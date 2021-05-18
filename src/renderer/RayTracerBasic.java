package renderer;

import elements.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;
import utility.tools;

import java.util.List;

import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracerBase {
    public RayTracerBasic(Scene _scene) {
        super(_scene);
    }

    private static final double DELTA = 0.1;
    /**
     * traces ray and calcs color
     * @param ray ray to trace
     * @return
     */
    @Override
    public Color traceRay(Ray ray) {
        var intersectois = scene.geometries.findGeoIntersections(ray);
        if (intersectois == null) {
            return scene.background;
        }
        var point = ray.getClosestGeoPoint(intersectois);
        return calcColor(point,ray);


    }

    /**
     * calcs color at goepoint
     * @param point point
     * @param ray ray
     * @return
     */
    Color calcColor(GeoPoint point, Ray ray) {

        return scene.ambientLight.getIntensity().add(point.geometry.getEmission())
                .add(calcLocalEffects(point,ray));
    }

    /**
     * clacs local effect
     * @param intersection intersection point
     * @param ray ray
     * @return
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
        Vector v = ray.getDir();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return Color.BLACK;
        int nShininess = intersection.geometry.getMaterial().getnShininess();
        double kd = intersection.geometry.getMaterial().getkD(), ks = intersection.geometry.getMaterial().getkS();
        Color color = Color.BLACK;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) {// sign(nl) == sing(nv)
                if (unshaded(lightSource,l,n, intersection)) {
                    Color lightIntensity = lightSource.getIntensity(intersection.point);
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

    /**
     * @param light light source
     * @param l l
     * @param n n
     * @param geopoint point and geometry
     * @return true if the point isn't shaded
     */
    private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : - DELTA);
        Point3D point = geopoint.point.add(delta);
        Ray lightRay = new Ray(point, lightDirection);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null) return true;
        double lightDistance = light.getDistance(geopoint.point);
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(geopoint.point)-lightDistance) <= 0)
                return false;
        }
        return true;
    }



}

