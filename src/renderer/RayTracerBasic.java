package renderer;

import elements.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;
import utility.tools;

import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracerBase {
    public RayTracerBasic(Scene _scene) {
        super(_scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        var intersectois = scene.geometries.findGeoIntersections(ray);
        if (intersectois == null) {
            return scene.background;
        }
        var point = ray.getClosestGeoPoint(intersectois);
        return calcColor(point,ray);


    }

    Color calcColor(GeoPoint point, Ray ray) {

        return scene.ambientLight.getIntensity().add(point.geometry.getEmission())
                .add(calcLocalEffects(point,ray));
    }

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
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Color lightIntensity = lightSource.getIntensity(intersection.point);
                color = color.add(lightIntensity.scale(calcDiffusive(kd, l, n)+ calcSpecular(ks, l, n, v, nShininess)));
            }
        }
        return color;
    }
   private double calcDiffusive(double kd,Vector l ,Vector n) {
       return kd*(Math.abs(l.dotProduct(n)));//add argument l*n
   }
    private double calcSpecular(double ks,Vector l ,Vector n,Vector v,int nShininess ) {
        Vector temp= n.scale(2*l.dotProduct(n));
        Vector r = l.subtract(temp);
        Vector temp2= v.scale(-1);
        double num= tools.max(0,temp2.dotProduct(r));
        return ks*(Math.pow(num,nShininess));
    }

}

