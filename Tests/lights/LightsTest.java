
package lights;

import org.junit.jupiter.api.Test;

import elements.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;


/**
 * Test rendering a basic image
 *Color
 * @author Dan
 */

 class LightsTests {
    private Scene scene1 = new Scene("Test scene");
    private Scene scene2 = new Scene("Test scene") //
            .setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
    private Camera camera1 = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setViewPlaneSize(150, 150) //
            .setDistance(1000);
    private Camera camera2 = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
            .setViewPlaneSize(200, 200) //
            .setDistance(1000);

    private static Geometry triangle1 = new Triangle( //
            new Point3D(-150, -150, -150), new Point3D(150, -150, -150), new Point3D(75, 75, -150));
    private static Geometry triangle2 = new Triangle( //
            new Point3D(-150, -150, -150), new Point3D(-70, 70, -50), new Point3D(75, 75, -150));
    private static Geometry sphere = new Sphere(50, new Point3D(0, 0, -50)) //
            .setEmission(new Color(java.awt.Color.BLUE)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100));
    private static Geometry sphere2 = new Sphere(50, new Point3D(0, 0, -50)) //
            .setEmission(new Color(java.awt.Color.BLACK)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100));


/**
     * Produce a picture of a sphere lighted by a directional light
     */

    @Test
    public void sphereDirectional() {
        scene1.geometries.add(sphere);
        scene1.lights.add(new DirectionalLight(new Color(500, 300, 0), new Vector(1, 1, -1)));

        ImageWriter imageWriter = new ImageWriter("sphereDirectional", 500, 500);
        Render render = new Render()//
                .setImageWriter(imageWriter) //
                .setCamera(camera1) //
                .setRayTracer(new BasicRayTracer(scene1));
        render.renderImage();
        render.writeToImage();
    }


/**
     * Produce a picture of a sphere lighted by a point light
     */

    @Test
    public void spherePoint() {
        scene1.geometries.add(sphere);
        scene1.lights.add(new PointLight(new Color(500, 300, 0), new Point3D(-50, -50, 50)).setkC(1).setKl(0.00001).setKq(0.000001));

        ImageWriter imageWriter = new ImageWriter("spherePoint", 500, 500);
        Render render = new Render()//
                .setImageWriter(imageWriter) //
                .setCamera(camera1) //
                .setRayTracer(new BasicRayTracer(scene1));
        render.renderImage();
        render.writeToImage();
    }

/**
     * Produce a picture of a sphere lighted by a spot light
     */

    @Test
    public void sphereSpot() {
        scene1.geometries.add(sphere);
        scene1.lights.add(new SpotLight(new Color(500, 300, 0), new Point3D(-50, -50, 50), new Vector(1, 1, -2)) //
                .setKl(0.00001).setKq(0.00000001));

        ImageWriter imageWriter = new ImageWriter("lightSphereSpot", 500, 500);
        Render render = new Render()//
                .setImageWriter(imageWriter) //
                .setCamera(camera1) //
                .setRayTracer(new BasicRayTracer(scene1));
        render.renderImage();
        render.writeToImage();
    }

    @Test
    public void sphereMulti() {
        scene1.geometries.add(sphere2);
        scene1.lights.add(new SpotLight(new Color(800, 0, 0), new Point3D(-10, -50, 20), new Vector(60, 80, 1)).setkC(1).setKl( 0.0000000000001).setKq(0.0000000000001));
        scene1.lights.add(new DirectionalLight(new Color(0, 300, 0), new Vector(1, 1, -1)));
        scene1.lights.add(new PointLight(new Color(0, 0, 1300), new Point3D(25, 10, 1)).setkC(1.1).setKl(0.0000000000000001).setKq(0.000000000000000001));

        ImageWriter imageWriter = new ImageWriter("sphereMulti", 500, 500);
        Render render = new Render()//
                .setImageWriter(imageWriter) //
                .setCamera(camera1) //
                .setRayTracer(new BasicRayTracer(scene1));
        render.renderImage();
        render.writeToImage();
    }

    /**
     * Produce a picture of a two triangles lighted by a directional light
     */

    @Test
    public void trianglesDirectional() {
        scene2.geometries.add(triangle1.setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)), //
                triangle2.setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)));
        scene2.lights.add(new DirectionalLight(new Color(300, 150, 150), new Vector(0, 0, -1)));

        ImageWriter imageWriter = new ImageWriter("trianglesDirectional", 500, 500);
        Render render = new Render()//
                .setImageWriter(imageWriter) //
                .setCamera(camera2) //
                .setRayTracer(new BasicRayTracer(scene2));
        render.renderImage();
        render.writeToImage();
    }


/**
     * Produce a picture of a two triangles lighted by a point light
     */

    @Test
    public void trianglesPoint() {
        scene2.geometries.add(triangle1.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)), //
                triangle2.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)));
        scene2.lights.add(new PointLight(new Color(500, 250, 250), new Point3D(10, -10, -130)).setkC(1).setKl(0.0005).setKq(0.0005));

        ImageWriter imageWriter = new ImageWriter("trianglesPoint", 500, 500);
        Render render = new Render()//
                .setImageWriter(imageWriter) //
                .setCamera(camera2) //
                .setRayTracer(new BasicRayTracer(scene2));
        render.renderImage();
        render.writeToImage();
    }


/**
     * Produce a picture of a two triangles lighted by a spot light
     */

    @Test
    public void trianglesSpot() {
        scene2.geometries.add(triangle1.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)),
                triangle2.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)));
        scene2.lights.add(new SpotLight(new Color(500, 250, 250), new Point3D(10, -10, -130), new Vector(-2, -2, -1)) //
                .setKl(0.0001).setKq(0.000005));

        ImageWriter imageWriter = new ImageWriter("lightTrianglesSpot", 500, 500);
        Render render = new Render()//
                .setImageWriter(imageWriter) //
                .setCamera(camera2) //
                .setRayTracer(new BasicRayTracer(scene2));
        render.renderImage();
        render.writeToImage();
    }

    @Test
    public void trianglesMulti() {
        scene2.geometries.add(triangle1.setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)), //
                triangle2.setMaterial(new Material().setKd(0.8).setKs(0.2).setShininess(300)));
        scene2.lights.add(new SpotLight(new Color(1000, 100, 80), new Point3D(10, -10, -130), new Vector(-2, -2, -1)) //
                        .setKl(0.0001).setKq(0.000005));
        scene2.lights.add(new DirectionalLight(new Color(0, 300, 0), new Vector(1, 1, -1)));
        scene2.lights.add(new PointLight(new Color(0, 0, 1300), new Point3D(10, -10, -130)).setkC(1).setKl(0.0005).setKq(0.0005));

        ImageWriter imageWriter = new ImageWriter("trianglesMulti", 500, 500);
        Render render = new Render()//
                .setImageWriter(imageWriter) //
                .setCamera(camera2) //
                .setRayTracer(new BasicRayTracer(scene2));
        render.renderImage();
        render.writeToImage();
    }

/**
     * Produce a picture of a sphere lighted by a narrow spot light
     */
/*
    @Test
    public void sphereSpotSharp() {
        scene1.geometries.add(sphere);
        scene1.lights.add(new SpotLight(new Color(500, 300, 0), new Point3D(-50, -50, 50), new Vector(1, 1, -2), 1,
                0.000005, 0.00000025, 5));

        ImageWriter imageWriter = new ImageWriter("sphereSpotSharp", 500, 500);
        Render render = new Render()//
                .setImageWriter(imageWriter) //
                .setScene(scene1) //
                .setCamera(camera1) //
                .setRayTracer(new RayTracerBasic(scene1));
        render.renderImage();
        render.writeToImage();
    }


/**
     * Produce a picture of a two triangles lighted by a narrow spot light
     */
/*
    @Test
    public void trianglesSpotSharp() {
        scene2.geometries.add(triangle1.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300)),
                triangle2.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(300)));
        scene2.lights.add(new SpotLight(new Color(800, 400, 400), new Point3D(10, -10, -130), 0.0000025, 1,
                0.00005, new Vector(-2, -2, -1), 5));

        ImageWriter imageWriter = new ImageWriter("trianglesSpotSharp", 500, 500);
        Render render = new Render()//
                .setImageWriter(imageWriter) //
                .setScene(scene2) //
                .setCamera(camera2) //
                .setRayTracer(new RayTracerBasic(scene2));
        render.renderImage();
        render.writeToImage();
    }
*/
}

