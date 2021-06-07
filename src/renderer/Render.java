package renderer;

import elements.Camera;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;
import algo.SuperSampling;

import java.util.List;
import java.util.MissingResourceException;

public class Render {
    private ImageWriter imageWriter;
    private Camera camera;
    private RayTracerBase rayTracer;
    private static  int COUNT_RAYS=100;
    public static  boolean aaFlag=false;
    private static String samplingAlgo="RANDOM";
    private  static  double pixelSize;


    public Render setImageWriter(ImageWriter imageWriter) {
        this.imageWriter=imageWriter;
        return this;
    }

    public Render setSamplingAlgo(String samplingAlgo) {
        if(samplingAlgo!="RANDOM" && samplingAlgo!="DISTRIBUTED")
            throw new IllegalArgumentException("you must select one of the two allowed algorithems");
        Render.samplingAlgo = samplingAlgo;
        return this;
    }

    public  Render setCountRays(int countRays) {
        COUNT_RAYS = countRays;
        return this;
    }

    public  Render setAAFlag(boolean flag) {
        Render.aaFlag = flag;
        return this;
    }



    public Render setScene(Scene scene) {
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

    /**
     *  renders image
     */
    public void renderImage() {
        if(imageWriter==null){
            throw new MissingResourceException("missing imagewirter","Render","ImageWriter");
        }
//        if(scene==null){
//            throw new MissingResourceException("missing scene","Render","scene");
//        }
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
        if(aaFlag){
            pixelSize= camera.getHeight()/(double)nY;
            if(pixelSize==0.0d)
                throw new RuntimeException("Pixelsize is zero\n");
        }
        for(int i=0;i<nY;i++){
            for(int j=0;j<nX;j++){
               ray= camera.constructRayThroughPixel(nX,nY,j,i);
               if(aaFlag){
                   color=new Color(Color.BLACK);
                   Point3D center=ray.getP0().add(ray.getDir().scale(this.camera.getDistance()));
                   List<Ray> sampleBeam;
                   //if(samplingAlgo=="DISTRIBUTED")
                       sampleBeam=SuperSampling.beam(ray,center, camera.getHeight()/nY,camera.getWidth()/nX,COUNT_RAYS);
                 //  else
                    //   sampleBeam=SuperSampling.randomSample(ray,center,COUNT_RAYS,pixelSize);
                    sampleBeam.add(ray);
                  for(var beamRay: sampleBeam){
                      color.add(rayTracer.traceRay(beamRay));
                  }
                      color.scale(1/sampleBeam.size());
               }
               else{
                    color= rayTracer.traceRay(ray);
               }
               if(color==null)
                  color=Color.BLACK;
               imageWriter.writePixel(j,i,color);
            }
        }
    }

    /**
     *  prints the grid on the picture
     * @param interval grid interval
     * @param color grid color
     */
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

    /**
     * writes to image
     */
    public void writeToImage() {
        if(imageWriter==null){
            throw new MissingResourceException("missing imagewirter","Render","ImageWriter");
        }
        imageWriter.writeToImage();
    }
}
