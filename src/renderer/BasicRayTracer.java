package renderer;

import algo.SuperSampling;
import elements.LightSource;
import elements.PointLight;
import elements.SpotLight;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;

public class BasicRayTracer extends RayTracerBase {
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final double INITIAL_K = 1.0;
    private static  int COUNT_RAYS=100;
    private static  double RADIUS_SAMPLE=10.0;
    public static  boolean flagSoftShadows=false;
    private static String samplingAlgo="RANDOM";


    public BasicRayTracer(Scene _scene) {
        super(_scene);
    }

    public BasicRayTracer setSamplingAlgo(String samplingAlgo) {
        if(samplingAlgo!="RANDOM" && samplingAlgo!="DISTRIBUTED")
            throw new IllegalArgumentException("you must select one of the two allowed algorithems");
        BasicRayTracer.samplingAlgo = samplingAlgo;
        return this;
    }

    public  BasicRayTracer setCountRays(int countRays) {
        COUNT_RAYS = countRays;
        return this;
    }

    public  BasicRayTracer setFlagSoftShadows(boolean flagSoftShadows) {
        BasicRayTracer.flagSoftShadows = flagSoftShadows;
        return this;
    }

    public  BasicRayTracer setRadiusSample(double radiusSample) {
        RADIUS_SAMPLE = radiusSample;
        return this;
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
        double num= Math.max(0,temp2.dotProduct(r));
        return ks*(Math.pow(num,nShininess));
    }

    private double transparency(LightSource ls, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        double sumKtr = 0.0, baseKtr;
        Ray lightRay = new Ray(geopoint.point, lightDirection, n);
        baseKtr = calKtr( geopoint, lightRay,ls);

        if ( flagSoftShadows) {
            List<Ray> beam;
            if(samplingAlgo=="DISTRIBUTED")
                beam = SuperSampling.distributedSample(lightRay,
                    geopoint.point.add(lightDirection.scale(ls.getDistance(geopoint.point))), COUNT_RAYS,RADIUS_SAMPLE);
            else
                beam = SuperSampling.randomSample(lightRay,
                        geopoint.point.add(lightDirection.scale(ls.getDistance(geopoint.point))), COUNT_RAYS,RADIUS_SAMPLE);
            for (int i = 1; i < beam.size(); i++) {
                sumKtr += calKtr(geopoint, beam.get(i),ls);
            }
            baseKtr = (sumKtr + baseKtr) / beam.size();
        }

        return baseKtr;
    }


    private double calKtr(GeoPoint geoPoint,Ray ray,LightSource ls){
        List<GeoPoint> intersections=scene.geometries.findGeoIntersections(ray);
        double lightDistance=ls.getDistance(geoPoint.point);
        double ktr=1.0;
        if(intersections==null){
            return ktr;
        }
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(geoPoint.point)-lightDistance) <= 0) {
                ktr *= gp.geometry.getMaterial().getKt();
                if (ktr < MIN_CALC_COLOR_K){
                    return 0.0;
                }
            }
        }
        return ktr;
    }



}

