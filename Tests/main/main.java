package main;

import geometries.Cylinder;
import org.junit.jupiter.api.Test;
import elements.*;
import geometries.Sphere;
import geometries.Triangle;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 *
 * @author dzilb
 */
public class main {
    private Scene scene = new Scene("Test scene").setAmbientLight(new AmbientLight(new Color(java.awt.Color.CYAN),1) );

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void cylnder() {
        Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setViewPlaneSize(150, 150).setDistance(1000);

        scene.geometries.add(new Cylinder(new Ray(new Point3D(0, 0, 0), new Vector(0, 0, 1)), 10, 20)
                .setEmission(new Color(java.awt.Color.green)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)));

        scene.lights.add( //
                new PointLight(new Color(1000, 600, 0), new Point3D(-100, -100, 500)) //
                        .setKl(0.0004).setKq(0.0000006));


        Render render = new Render() //
                .setImageWriter(new ImageWriter("cylTets", 500, 500)) //
                .setCamera(camera) //
                .setRayTracer(new BasicRayTracer(scene));
        render.renderImage();
        render.writeToImage();
    }

}