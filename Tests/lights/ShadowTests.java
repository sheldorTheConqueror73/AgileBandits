package lights;

import org.junit.jupiter.api.Test;

import elements.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;
/**
 * Testing basic shadows
 *
 * @author Dan
 */
public class ShadowTests {
    private Scene scene = new Scene("Test scene");
    private Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
            .setViewPlaneSize(200, 200).setDistance(1000);


    /**
     * Produce a picture of a sphere and triangle with point light and shade
     */
    @Test
    public void sphereTriangleInitial() {
        scene.geometries.add( //
                new Sphere(60, new Point3D(0, 0, -200)) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)), //
                new Triangle(new Point3D(-70, -40, 0), new Point3D(-40, -70, 0), new Point3D(-68, -68, -4)) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)) //
        );
        scene.lights.add( //
                new SpotLight(new Color(400, 240, 0), new Point3D(-100, -100, 200), new Vector(1, 1, -3)) //
                        .setKl(1E-5).setKq(1.5E-7));

        Render render = new Render(). //
                setImageWriter(new ImageWriter("shadowSphereTriangleInitial", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new BasicRayTracer(scene));
        render.renderImage();
        render.writeToImage();
    }

    /**
     * Produce a picture of a two triangles lighted by a spot light with a Sphere
     * producing a shading
     */
    @Test
    public void trianglesSphere() {
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.geometries.add( //
                new Triangle(new Point3D(-150, -150, -115), new Point3D(150, -150, -135), new Point3D(75, 75, -150)) //
                        .setMaterial(new Material().setKs(0.8).setShininess(60)), //
                new Triangle(new Point3D(-150, -150, -115), new Point3D(-70, 70, -140), new Point3D(75, 75, -150)) //
                        .setMaterial(new Material().setKs(0.8).setShininess(60)), //
                new Sphere(30, new Point3D(0, 0, -115)) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)) //
        );
        scene.lights.add( //
                new SpotLight(new Color(700, 400, 400), new Point3D(40, 40, 115), new Vector(-1, -1, -4)) //
                        .setKl(4E-4).setKq(2E-5));

        Render render = new Render() //
                .setImageWriter(new ImageWriter("shadowTrianglesSphere", 600, 600)) //
                .setCamera(camera) //
                .setRayTracer(new BasicRayTracer(scene));
        render.renderImage();
        render.writeToImage();
    }

    @Test
    public void sphereTriangleGeoPos1() {
        scene.geometries.add( //
                new Sphere(60, new Point3D(0, 0, -200)) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)), //
                new Triangle(new Point3D(-60, -30, 0), new Point3D(-30, -60, 0), new Point3D(-58, -58, -4)) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)) //
        );
        scene.lights.add( //
                new SpotLight(new Color(400, 240, 0), new Point3D(-100, -100, 200), new Vector(1, 1, -3)) //
                        .setKl(1E-5).setKq(1.5E-7));

        Render render = new Render(). //
                setImageWriter(new ImageWriter("sphereTriangleGeoPos1", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new BasicRayTracer(scene));
        render.renderImage();
        render.writeToImage();
    }

    @Test
    public void sphereTriangleGeoPos2() {
        scene.geometries.add( //
                new Sphere(60, new Point3D(0, 0, -200)) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)), //
                new Triangle(new Point3D(-47, -17, -10), new Point3D(-17, -47, -10), new Point3D(-45, -45, -14)) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)) //
        );
        scene.lights.add( //
                new SpotLight(new Color(400, 240, 0), new Point3D(-100, -100, 200), new Vector(1, 1, -3)) //
                        .setKl(1E-5).setKq(1.5E-7));

        Render render = new Render(). //
                setImageWriter(new ImageWriter("sphereTriangleGeoPos2", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new BasicRayTracer(scene));
        render.renderImage();
        render.writeToImage();
    }

    @Test
    public void trianglesSphereLightPos1() {
            scene.geometries.add( //
                    new Sphere(60, new Point3D(0, 0, -200)) //
                            .setEmission(new Color(java.awt.Color.BLUE)) //
                            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)), //
                    new Triangle(new Point3D(-70, -40, 0), new Point3D(-40, -70, 0), new Point3D(-68, -68, -4)) //
                            .setEmission(new Color(java.awt.Color.BLUE)) //
                            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)) //
            );
            scene.lights.add( //
                    new SpotLight(new Color(400, 240, 0), new Point3D(-85, -85, 130), new Vector(1, 1, -3)) //
                            .setKl(1E-5).setKq(1.5E-7));

            Render render = new Render(). //
                    setImageWriter(new ImageWriter("trianglesSphereLightPos1", 400, 400)) //
                    .setCamera(camera) //
                    .setRayTracer(new BasicRayTracer(scene));
            render.renderImage();
            render.writeToImage();
        }

    @Test
    public void trianglesSphereLightPos2() {
        scene.geometries.add( //
                new Sphere(60, new Point3D(0, 0, -200)) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)), //
                new Triangle(new Point3D(-70, -40, 0), new Point3D(-40, -70, 0), new Point3D(-68, -68, -4)) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)) //
        );
        scene.lights.add( //
                new SpotLight(new Color(400, 240, 0), new Point3D(-73, -73, 55), new Vector(1, 1, -3)) //
                        .setKl(1E-5).setKq(1.5E-7));

        Render render = new Render(). //
                setImageWriter(new ImageWriter("trianglesSphereLightPos2", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new BasicRayTracer(scene));
        render.renderImage();
        render.writeToImage();
    }


    @Test
    public void trianglesPyramid() {
        long time=java.lang.System.nanoTime();
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.geometries.add( //
                new Triangle(new Point3D(-150, -150, -115), new Point3D(150, -150, -135), new Point3D(75, 75, -150)) //
                        .setMaterial(new Material().setKs(0.8).setShininess(60)), //
                new Triangle(new Point3D(-150, -150, -115), new Point3D(-70, 70, -140), new Point3D(75, 75, -150)) //
                        .setMaterial(new Material().setKs(0.8).setShininess(60)), //
                new Pyramid(new Rectangle(new Point3D(-50,0,-100),new Point3D(-50,-60,-100),new Point3D(0,-60,-100),new Point3D(0,0,-100)), new Point3D(-25,-30,0))
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30))
                        .setEmission2(new Color(java.awt.Color.RED),1) //
                        .setEmission2(new Color(java.awt.Color.GREEN),2) //
                        .setEmission2(new Color(java.awt.Color.CYAN),3) //
        );
        scene.lights.add( //
                new SpotLight(new Color(700, 400, 400).scale(0.9), new Point3D(40, 40, 115), new Vector(-1, -1, -4)) //
                        .setKl(4E-4).setKq(2E-5));
//        scene.lights.add(new PointLight(new Color(700, 400, 400).scale(0.9), new Point3D(-40, -40, 115)) //
//                .setKl(4E-4).setKq(2E-5));

        Render render = new Render().setMultithreading(3).setAdaptiveSampleFlag(false)
                .setImageWriter(new ImageWriter("shadowTrianglesPyramid", 600, 600)) //
                .setCamera(camera) //
                .setRayTracer(new BasicRayTracer(scene).setFlagSoftShadows(false));
        render.renderImage();
        render.writeToImage();

        long end=java.lang.System.nanoTime()-time;
        System.out.println(end/1000000000);
    }


    @Test
    public void SoftShadowTest(){
        long time=java.lang.System.nanoTime();
        scene = new Scene("Test scene");
        camera = new Camera(new Point3D(-1000, 450, 1000), new Vector(1, -0.4, -1), new Vector(1, 0, 1)) //
                .setViewPlaneSize(450, 450).setDistance(1000);
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
        Material material=new Material().setKd(0.2).setKs(0.2).setShininess(30);

        scene.geometries.add( //
                new Polygon(
                        new Point3D(-400, 400, 0), new Point3D(-400, -400, 0), new Point3D(200, -400, 0),new Point3D(200,400,0))
                        .setMaterial(material),//
                new Rectangle(new Point3D(0, 80, 150), new Point3D(0, 20, 150), new Point3D(-80, 0, 150),new Point3D(-80, 80, 150)).setEmission(new Color(10,60,80)) .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(1)),
                new Sphere(10,new Point3D(0, 80, 150)).setEmission(new Color(java.awt.Color.WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(0.5)),
                new Sphere(10,new Point3D(0, 20, 150)).setEmission(new Color(java.awt.Color.WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(0.5)),
                new Sphere(10,new Point3D(-80, 0, 150)).setEmission(new Color(java.awt.Color.WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(0.5)),
                new Sphere(10,new Point3D(-80, 80, 150)).setEmission(new Color(java.awt.Color.WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(0.5)),
                new Pyramid(new Rectangle(new Point3D(0, 50, 0), new Point3D(-50, 0, 0), new Point3D(-100, 50, 0),new Point3D(-50, 100, 0)),new Point3D(-50, 50, 150)).setEmission(new Color(10,60,80)) .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(1)),
                new Pyramid(new Rectangle(new Point3D(0, 50, 300), new Point3D(-50, 0, 300), new Point3D(-100, 50, 300),new Point3D(-50, 100, 300)),new Point3D(-50, 50, 150)).setEmission(new Color(java.awt.Color.BLUE)) .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(1)).setEmission2(new Color(50,50,200),2),
                new Sphere(50,new Point3D(-180, -50, 50)).setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(0.5)),
                new Sphere(50,new Point3D(-175, 175, 50)).setEmission(new Color(255,128,0).scale(0.4)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(0.5)),
                new Sphere(50,new Point3D(65, 225, 50)).setEmission(new Color(java.awt.Color.GREEN).scale(0.3)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(0.5)),
                new Sphere(50,new Point3D(45, -110, 50)).setEmission(new Color(0,0,0))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(0.5)));




        scene.lights.add(new PointLight(new primitives.Color(700, 400, 400).scale(0.9), //
                new Point3D(0, 80, 150)).setkC(1).setKl(4E-4).setKq(2E-5));
        scene.lights.add(new PointLight(new primitives.Color(700, 400, 400).scale(0.9), //
                new Point3D(0, 20, 150)).setkC(1).setKl(4E-4).setKq(2E-5));
        scene.lights.add(new PointLight(new primitives.Color(700, 400, 400).scale(0.9), //
                new Point3D(-80, 0, 150)).setkC(1).setKl(4E-4).setKq(2E-5));
        scene.lights.add(new PointLight(new primitives.Color(700, 400, 400).scale(0.9), //
                new Point3D(-80, 80, 150)).setkC(1).setKl(4E-4).setKq(2E-5));

        scene.lights.add(new PointLight(new primitives.Color(700, 400, 400).scale(0.8), //
                new Point3D(-50, 50, 800)).setkC(1).setKl(4E-4).setKq(2E-5));
        scene.lights.add(new PointLight(new primitives.Color(700, 400, 400).scale(0.4), //
                new Point3D(-100, 50, 150)).setkC(1).setKl(4E-4).setKq(2E-5));


        scene.lights.add(new SpotLight(new Color(1020, 400, 400).scale(0.3), new Point3D(-150, 150, 350), new Vector(1, -1, -1)) //
                .setKl(0.00001).setKq(0.000005));
        scene.lights.add(new SpotLight(new Color(1020, 400, 400).scale(0.3), new Point3D(0, 0, 350), new Vector(-1, 1, -1)) //
                .setKl(0.00001).setKq(0.000005));

        Render render = new Render().setAdaptiveSampleFlag(false) //
                .setImageWriter(new ImageWriter("softShadows4", 600, 600)) //
                .setCamera(camera) //
                .setRayTracer(new BasicRayTracer(scene).setFlagSoftShadows(true));
        render.renderImage();
        render.writeToImage();

        long end=java.lang.System.nanoTime()-time;
        System.out.println(end/1000000000);
    }


    @Test
    public void MiniProject2(){
        long time=java.lang.System.nanoTime();
        scene = new Scene("Test scene");
        camera = new Camera(new Point3D(-1720, 450, 420), new Vector(1, -0.3, -0.3), new Vector(1, 0, (10/3d))) //
                .setViewPlaneSize(450, 450).setDistance(1000);
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
        Material material=new Material().setKd(0.2).setKs(0.2).setShininess(30);

        Color colorLegs=new Color(163, 181, 80);
        double krLegs=0.35;

        scene.geometries.add( //
                new Plane(
                        new Point3D(-1, 0, -300), new Point3D(1, 0, -300), new Point3D(0, 1, -300))
                        .setMaterial(material).setEmission(new Color(126, 133, 93).scale(0.11)),//
//                new Rectangle(new Point3D(0, 50, 0), new Point3D(0, 0, 0), new Point3D(-50, 0, 0),new Point3D(-50, 50, 0)).setEmission(new Color(10,60,80)) .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(1)),

                new Sphere(10,new Point3D(0, 50, 150)).setEmission(new Color(java.awt.Color.WHITE).scale(0.4))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKt(0.2)),
                new Sphere(10,new Point3D(-50, 0, 150)).setEmission(new Color(java.awt.Color.WHITE).scale(0.4))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKt(0.2)),
                new Sphere(10,new Point3D(-50, 100, 150)).setEmission(new Color(java.awt.Color.WHITE).scale(0.4))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKt(0.2)),
                new Sphere(10,new Point3D(-100, 50, 150)).setEmission(new Color(java.awt.Color.WHITE).scale(0.4))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKt(0.2)),

                new Pyramid(new Rectangle(new Point3D(0, 50, 150), new Point3D(-50, 0, 150), new Point3D(-100, 50, 150),new Point3D(-50, 100, 150)),new Point3D(-50, 50, 0)).setEmission(new Color(java.awt.Color.BLUE)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(200).setKr(0.4)),

                new Sphere(50,new Point3D(-220, -200, 50)).setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(0.5)),
                new Sphere(50,new Point3D(-215, 220, 50)).setEmission(new Color(255,128,0).scale(0.4)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(0.5)),
                new Sphere(50,new Point3D(65, 225, 50)).setEmission(new Color(java.awt.Color.GREEN).scale(0.3)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(0.5)),
                new Sphere(50,new Point3D(65, -185, 50)).setEmission(new Color(0,0,0))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(0.5)),

                new Sphere(10,new Point3D(-50, 50, -200)).setEmission(new Color(java.awt.Color.WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKt(0.8)),

               new Polygon(new Point3D(200,300,0),new Point3D(200,-300,0),new Point3D(-300,-300,0),new Point3D(-300,300,0)).setEmission(new Color(163, 181, 80).scale(0.6)) //
                       .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(0.35).setKt(0.05)),//base table

                //sides of table:
                new Rectangle(new Point3D(-300,300,0),new Point3D(-300,-300,0),new Point3D(-300,-300,-20),new Point3D(-300,300,-20)).setEmission(colorLegs.scale(0.6)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(krLegs)),
                new Rectangle(new Point3D(200,300,0),new Point3D(-200,300,0),new Point3D(-200,300,-20),new Point3D(200,300,-20)).setEmission(colorLegs.scale(0.6)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(krLegs)),
                new Rectangle(new Point3D(200,-300,0),new Point3D(200,300,0),new Point3D(200,300,-20),new Point3D(200,-300,-20)).setEmission(colorLegs.scale(0.6)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(krLegs)),
                new Rectangle(new Point3D(-300,-300,0),new Point3D(200,-300,0),new Point3D(200,-300,-20),new Point3D(-300,-300,-20)).setEmission(colorLegs.scale(0.6)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(krLegs)),
                new Rectangle(new Point3D(200,300,-20),new Point3D(200,-300,-20),new Point3D(-300,-300,-20),new Point3D(-300,300,-20)).setEmission(colorLegs.scale(0.6)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(krLegs)),
                //legs:
                new Rectangle(new Point3D(-300,300,-20),new Point3D(-300,290,-20),new Point3D(-300,290,-300),new Point3D(-300,300,-300)).setEmission(colorLegs.scale(0.6)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(krLegs)),
                new Rectangle(new Point3D(-290,300,-20),new Point3D(-300,300,-20), new Point3D(-300,300,-300),new Point3D(-290,300,-300)).setEmission(colorLegs.scale(0.6)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(krLegs)),
                new Rectangle(new Point3D(-290,290,-20),new Point3D(-290,300,-20),new Point3D(-290,300,-300),new Point3D(-290,290,-300)).setEmission(colorLegs.scale(0.6)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(krLegs)),
                new Rectangle(new Point3D(-300,290,-20),new Point3D(-290,290,-20),new Point3D(-290,290,-300),new Point3D(-300,290,-300)).setEmission(colorLegs.scale(0.6)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(krLegs)),//leg 1

                new Rectangle(new Point3D(200,300,-20),new Point3D(200,290,-20),new Point3D(200,290,-300),new Point3D(200,300,-300)).setEmission(colorLegs.scale(0.6)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(krLegs)),
                new Rectangle(new Point3D(200,300,-20),new Point3D(190,300,-20),new Point3D(190,300,-300),new Point3D(200,300,-300)).setEmission(colorLegs.scale(0.6)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(krLegs)),
                new Rectangle(new Point3D(200,290,-20),new Point3D(200,300,-20),new Point3D(200,300,-300),new Point3D(200,290,-300)).setEmission(colorLegs.scale(0.6)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(krLegs)),
                new Rectangle(new Point3D(190,290,-20),new Point3D(200,290,-20),new Point3D(200,290,-300),new Point3D(190,290,-300)).setEmission(colorLegs.scale(0.6)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(krLegs)),//leg 2

                new Rectangle(new Point3D(190,-290,-20),new Point3D(190,-300,-20),new Point3D(190,-300,-300),new Point3D(190,-290,-300)).setEmission(colorLegs.scale(0.6)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(krLegs)),
                new Rectangle(new Point3D(200,-290,-20),new Point3D(190,-290,-20),new Point3D(190,-290,-300),new Point3D(200,-290,-300)).setEmission(colorLegs.scale(0.6)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(krLegs)),
                new Rectangle(new Point3D(200,-300,-20),new Point3D(200,-290,-20),new Point3D(200,-290,-300),new Point3D(200,-300,-300)).setEmission(colorLegs.scale(0.6)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(krLegs)),
                new Rectangle(new Point3D(190,-300,-20),new Point3D(200,-300,-20),new Point3D(200,-300,-300),new Point3D(190,-300,-300)).setEmission(colorLegs.scale(0.6)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(krLegs)),//leg 3

                new Rectangle(new Point3D(-300,-290,-20),new Point3D(-300,-300,-20),new Point3D(-300,-300,-300),new Point3D(-300,-290,-300)).setEmission(colorLegs.scale(0.6)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(krLegs)),
                new Rectangle(new Point3D(-290,-290,-20),new Point3D(-300,-290,-20),new Point3D(-300,-290,-300),new Point3D(-290,-290,-300)).setEmission(colorLegs.scale(0.6)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(krLegs)),
                new Rectangle(new Point3D(-290,-300,-20),new Point3D(-290,-290,-20),new Point3D(-290,-290,-300),new Point3D(-290,-300,-300)).setEmission(colorLegs.scale(0.6)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(krLegs)),
                new Rectangle(new Point3D(-300,-300,-20),new Point3D(-290,-300,-20),new Point3D(-290,-300,-300),new Point3D(-300,-300,-300)).setEmission(colorLegs.scale(0.6)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(krLegs)),//leg 4



                new Sphere(5,new Point3D(40, -60, -295)).setEmission(new Color(java.awt.Color.BLUE)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(0.5)),
                new Sphere(5,new Point3D(58, -55, -295)).setEmission(new Color(java.awt.Color.GREEN)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(0.5)),
                new Sphere(5,new Point3D(34, -47, -295)).setEmission(new Color(java.awt.Color.BLUE)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(0.5)),
                new Sphere(5,new Point3D(46, -46, -295)).setEmission(new Color(java.awt.Color.RED)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(0.5)),
                new Sphere(5,new Point3D(22, -35, -295)).setEmission(new Color(java.awt.Color.YELLOW)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(0.5)),
                new Sphere(5,new Point3D(36, -36, -295)).setEmission(new Color(java.awt.Color.CYAN)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(0.5)),
                new Sphere(5,new Point3D(33, -24, -295)).setEmission(new Color(java.awt.Color.MAGENTA)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(0.5)),
                new Sphere(5,new Point3D(49, -28, -295)).setEmission(new Color(java.awt.Color.ORANGE)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(0.5))
        );




        scene.lights.add(new PointLight(new primitives.Color(700, 400, 400).scale(1.2), //
                new Point3D(-550, 50, 500)).setkC(1).setKl(4E-4).setKq(2E-5));

        scene.lights.add(new PointLight(new primitives.Color(700, 400, 400).scale(0.4), //
                new Point3D(-50, 50, -200)).setkC(1).setKl(4E-4).setKq(2E-5));
//        scene.lights.add(new PointLight(new primitives.Color(700, 400, 400).scale(0.9), //
//                new Point3D(-80, 0, 150)).setkC(1).setKl(4E-4).setKq(2E-5));

        scene.lights.add(new PointLight(new primitives.Color(700, 400, 400).scale(1), //
                new Point3D(0, 50, 150)).setkC(1).setKl(4E-4).setKq(2E-5));
        scene.lights.add(new PointLight(new primitives.Color(700, 400, 400).scale(1), //
                new Point3D(0, 50, 150)).setkC(1).setKl(4E-4).setKq(2E-5));
        scene.lights.add(new PointLight(new primitives.Color(700, 400, 400).scale(1), //
                new Point3D(-50, 100, 150)).setkC(1).setKl(4E-4).setKq(2E-5));
        scene.lights.add(new PointLight(new primitives.Color(700, 400, 400).scale(1), //
                new Point3D(-100, 50, 150)).setkC(1).setKl(4E-4).setKq(2E-5));
//
//
        scene.lights.add(new SpotLight(new Color(1020, 400, 400).scale(0.5), new Point3D(-600, 50, 0), new Vector(1, 0, 0)) //
                .setKl(0.00001).setKq(0.000005));
//        scene.lights.add(new SpotLight(new Color(1020, 400, 400).scale(1), new Point3D(-50, 50, -250), new Vector(1, 0, -0.7)) //
//                .setKl(0.00001).setKq(0.000005));

        Render render = new Render().setMultithreading(3).setAdaptiveSampleFlag(false) //
                .setImageWriter(new ImageWriter("miniProject2V2", 600, 600)) //
                .setCamera(camera) //
                .setRayTracer(new BasicRayTracer(scene).setFlagSoftShadows(false));
        render.renderImage();
        render.writeToImage();

        long end=java.lang.System.nanoTime()-time;
        System.out.println(end/1000000000);
    }



}
