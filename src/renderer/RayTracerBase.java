package renderer;

import primitives.*;
import scene.*;


public abstract class RayTracerBase {
    protected Scene scene;

     public  RayTracerBase(Scene _scene){this.scene=_scene;};
     public abstract Color traceRay(Ray ray);
}
