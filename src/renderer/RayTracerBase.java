package renderer;

import primitives.*;
import scene.*;


public abstract class RayTracerBase {
    protected Scene scene;

     public  RayTracerBase(Scene _scene){this.scene=_scene;};
     public abstract Color traceRay(Ray ray);
    public abstract Color adaptiveTrace(Point3D c0,Point3D c1,Point3D c2,Point3D c3,Point3D camPos,int level );
}
