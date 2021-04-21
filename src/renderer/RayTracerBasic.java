package renderer;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

public class RayTracerBasic extends RayTracerBase{
    public RayTracerBasic(Scene _scene) {
        super(_scene);
    }

    @Override
    public Color traceRay(Ray ray) {
       var intersectois= scene.geometries.findIntersections(ray);
       if(intersectois==null){
           return scene.background;
       }
        Point3D point=ray.getClosestPoint(intersectois);
       return calcColor(point);
    }

    Color calcColor(Point3D point){
        return scene.ambientLight.getIntensity();
    }
}
