package renderer;

import geometries.Intersectable;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

public class RayTracerBasic extends RayTracerBase{
    public RayTracerBasic(Scene _scene) {
        super(_scene);
    }

    @Override
    public Color traceRay(Ray ray) {
       var intersectois= scene.geometries.findGeoIntersections(ray);
       if(intersectois==null){
           return scene.background;
       }
        var point=ray.getClosestGeoPoint(intersectois);
         return calcColor(point);


    }

    Color calcColor(GeoPoint point){
        return scene.ambientLight.getIntensity().add(point.geometry.getEmission());
    }
}
       /*  return scene.ambientLight.getIntensity().add(point!=null?point.geometry.getEmission():Color.BLACK);*/
