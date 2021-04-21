package renderer;

import elements.Camera;
import primitives.Color;
import primitives.Ray;
import scene.Scene;

import java.util.MissingResourceException;

public class Render {
    private ImageWriter imageWriter;
    private Scene scene;
    private Camera camera;
    private RayTracerBase rayTracer;

    public Render setImageWriter(ImageWriter imageWriter) {
        this.imageWriter=imageWriter;
        return this;
    }

    public Render setScene(Scene scene) {
        this.scene=scene;
        return this;
    }

    public Render setCamera(Camera camera) {
        this.camera=camera;
        return this;
    }

    public Render setRayTracer(RayTracerBase rayTracerBase) {
        this.rayTracer = rayTracerBase;
        return this;
    }

    public void renderImage() {
        if(imageWriter==null){
            throw new MissingResourceException("missing imagewirter","Render","ImageWriter");
        }
        if(scene==null){
            throw new MissingResourceException("missing scene","Render","scene");
        }
        if(camera==null){
            throw new MissingResourceException("missing camera","Render","camera");
        }
        if(rayTracer ==null){
            throw new MissingResourceException("missing rayTracerBase","Render","rayTracerBase");
        }

        Ray ray;
        Color color;
        int nX=imageWriter.getNx();
        int nY= imageWriter.getNy();
        for(int i=0;i<nY;i++){
            for(int j=0;j<nX;j++){
               ray= camera.constructRayThroughPixel(nX,nY,j,i);
               color= rayTracer.traceRay(ray);
               imageWriter.writePixel(j,i,color);
            }
        }
    }

    public void printGrid(int interval, Color color) {
        if(imageWriter==null){
            throw new MissingResourceException("missing imagewirter","Render","ImageWriter");
        }
        int nX=imageWriter.getNx();
        int nY= imageWriter.getNy();
        for(int i=0;i<nY;i++){
            for(int j=0;j<nX;j++){
                if(i%interval==0||j%interval==0){
                    imageWriter.writePixel(j,i,color);
                }
            }
        }
    }

    public void writeToImage() {
        if(imageWriter==null){
            throw new MissingResourceException("missing imagewirter","Render","ImageWriter");
        }
        imageWriter.writeToImage();
    }
}