package renderer;

import elements.Camera;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;
import algo.SuperSampling;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;

public class Render {
    private ImageWriter imageWriter;
    private Camera camera;
    private RayTracerBase rayTracer;
    private static boolean adaptiveSampleFlag = false;
    private static int adaptiveSampleLevel=3;

    public Render setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    public Render setAdaptiveSampleLevel(int adaptiveSampleLevel) {
        Render.adaptiveSampleLevel = adaptiveSampleLevel;
        return this;
    }

    public Render setAdaptiveSampleFlag(boolean adaptiveSampleFlag) {
        Render.adaptiveSampleFlag = adaptiveSampleFlag;
        return this;
    }

    public Render setScene(Scene scene) {
        return this;
    }

    public Render setCamera(Camera camera) {
        this.camera = camera;
        return this;
    }

    public Render setRayTracer(RayTracerBase rayTracerBase) {
        this.rayTracer = rayTracerBase;
        return this;
    }

    /**
     * renders image
     */
    public void renderImage() {
        if (imageWriter == null) {
            throw new MissingResourceException("missing imagewirter", "Render", "ImageWriter");
        }
//        if(scene==null){
//            throw new MissingResourceException("missing scene","Render","scene");
//        }
        if (camera == null) {
            throw new MissingResourceException("missing camera", "Render", "camera");
        }
        if (rayTracer == null) {
            throw new MissingResourceException("missing rayTracerBase", "Render", "rayTracerBase");
        }

        Ray ray;
        Color color;
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                if (!adaptiveSampleFlag) {
                    ray = camera.constructRayThroughPixel(nX, nY, j, i);
                    color = rayTracer.traceRay(ray);

                } else {
                        List<Point3D> corners=camera.calcCorners(nX,nY,i,j);
                    color =  rayTracer.adaptiveTrace(corners.get(0),corners.get(1),corners.get(2),corners.get(3),camera.getP0(),adaptiveSampleLevel);
                }
                if (color == null)
                    color = Color.BLACK;
                imageWriter.writePixel(j, i, color);
            }


        }
    }

    /**
     * prints the grid on the picture
     *
     * @param interval grid interval
     * @param color    grid color
     */
    public void printGrid(int interval, Color color) {
        if (imageWriter == null) {
            throw new MissingResourceException("missing imagewirter", "Render", "ImageWriter");
        }
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                if (i % interval == 0 || j % interval == 0) {
                    imageWriter.writePixel(j, i, color);
                }
            }
        }
    }

    /**
     * writes to image
     */
    public void writeToImage() {
        if (imageWriter == null) {
            throw new MissingResourceException("missing imagewirter", "Render", "ImageWriter");
        }
        imageWriter.writeToImage();
    }
}

