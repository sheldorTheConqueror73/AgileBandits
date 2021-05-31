package renderer;

import elements.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;
import utility.tools;

import java.util.List;

import static primitives.Util.alignZero;

public class BasicRayTracer extends RayTracerBase {
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final double INITIAL_K = 1.0;



    public BasicRayTracer(Scene _scene) {
        super(_scene);
    }

    /**
     * traces ray and calcs color
     * @param ray ray to trace
     * @return
     */
    @Override
    public Color traceRay(Ray ray) {
        GeoPoint closestPoint = findClosestIntersection(ray);
        return closestPoint == null ? scene.background : calcColor(closestPoint, ray);

    }


    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        return calcColor(geoPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.ambientLight.getIntensity());
    }


    /**
     * calcs color at goepoint
     * @param point point
     * @param ray ray
     * @return
     */
    Color calcColor(GeoPoint point, Ray ray,int level,double k) {
       Color color=point.geometry.getEmission();
       color= color.add(calcLocalEffects(point,ray,k));
       return 1==level?color:color.add(calcGlobalEffects(point,ray.getDir(),level,k));


    }

    private Color calcGlobalEffects(GeoPoint gp, Vector v, int level, double k) {
        Color color = Color.BLACK; Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        double kkr = k * material.kR;
        if (kkr > MIN_CALC_COLOR_K)
            color = calcGlobalEffect(constructReflectedRay(n,gp.point,v), level, material.kR, kkr);
        double kkt = k * material.kT;
        if (kkt > MIN_CALC_COLOR_K)
            color = color.add(
                    calcGlobalEffect(constructRefractedRay(n,gp.point, v), level, material.kT, kkt));
        return color;
    }
    private Color calcGlobalEffect(Ray ray, int level, double kx, double kkx) {
        GeoPoint gp = findClosestIntersection(ray);
        if(gp==null){
            return scene.background.scale(kx);
        }
        Color temp=calcColor(gp, ray, level-1, kkx);
        return temp.scale(kx);
    }

    private Ray constructReflectedRay(Vector n, Point3D point, Vector v){
        double temp = v.dotProduct(n)*2;
        Vector r = v.subtract(n.scale(temp));
        return new Ray(point,r,n);
    }

    private Ray constructRefractedRay(Vector n,Point3D point,Vector v){
        return new Ray(point,v,n);
    }

    private GeoPoint findClosestIntersection(Ray ray){
        var points=scene.geometries.findGeoIntersections(ray);
        return ray.getClosestGeoPoint(points);
    }

    /**
     * clacs local effect
     * @param intersection intersection point
     * @param ray ray
     * @return
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray,double k) {
        Vector v = ray.getDir();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return Color.BLACK;
        int nShininess = intersection.geometry.getMaterial().getnShininess();
        double kd = intersection.geometry.getMaterial().getkD(),
                ks = intersection.geometry.getMaterial().getkS();
        Color color = Color.BLACK;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) {// sign(nl) == sing(nv)
                double ktr=transparency(lightSource,l,n, intersection);
                if (ktr * k > MIN_CALC_COLOR_K) {
                    Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);
                    color = color.add(lightIntensity.scale(calcDiffusive(kd, l, n) + calcSpecular(ks, l, n, v, nShininess)));
                }

            }
        }
        return color;
    }

    /**
     * calcs diffusive effect
     * @param kd kd coefficient
     * @param l l vector
     * @param n n vector
     * @return
     */
   private double calcDiffusive(double kd,Vector l ,Vector n) {
       return kd*(Math.abs(l.dotProduct(n)));//add argument l*n
   }

    /**
     * calcs specular effecr
     * @param ks ks coefficient
     * @param l  l vector
     * @param n n vector
     * @param v vector
     * @param nShininess shininess coefficient
     * @return
     */
    private double calcSpecular(double ks,Vector l ,Vector n,Vector v,int nShininess ) {
        Vector temp= n.scale(2*l.dotProduct(n));
        Vector r = l.subtract(temp);
        Vector temp2= v.scale(-1);
        double num= tools.max(0,temp2.dotProduct(r));
        return ks*(Math.pow(num,nShininess));
    }


    private double transparency(LightSource ls, Vector l, Vector n, GeoPoint geoPoint) {
        Point3D point=geoPoint.point;
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(point, lightDirection, n);
        double lightDistance = ls.getDistance(point);
        var intersections = scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null){
            return 1.0;
        }
        double ktr = 1.0;
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(point)-lightDistance) <= 0) {
                ktr *= gp.geometry.getMaterial().getKt();
                if (ktr < MIN_CALC_COLOR_K) return 0.0;
            }
        }
        return ktr;
    }


}

