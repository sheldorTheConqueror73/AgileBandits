package miniProject;

import org.junit.jupiter.api.Test;

import elements.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

public class MiniPorject {
    private Scene scene = new Scene("Test scene");
    private Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
            .setViewPlaneSize(200, 200).setDistance(1000);

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
                .setImageWriter(new ImageWriter("MP1softShadows3", 600, 600)) //
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

        scene.lights.add(new PointLight(new primitives.Color(700, 400, 400).scale(1), //
                new Point3D(0, 50, 150)).setkC(1).setKl(4E-4).setKq(2E-5));
        scene.lights.add(new PointLight(new primitives.Color(700, 400, 400).scale(1), //
                new Point3D(0, 50, 150)).setkC(1).setKl(4E-4).setKq(2E-5));
        scene.lights.add(new PointLight(new primitives.Color(700, 400, 400).scale(1), //
                new Point3D(-50, 100, 150)).setkC(1).setKl(4E-4).setKq(2E-5));
        scene.lights.add(new PointLight(new primitives.Color(700, 400, 400).scale(1), //
                new Point3D(-100, 50, 150)).setkC(1).setKl(4E-4).setKq(2E-5));

        scene.lights.add(new SpotLight(new Color(1020, 400, 400).scale(0.5), new Point3D(-600, 50, 0), new Vector(1, 0, 0)) //
                .setKl(0.00001).setKq(0.000005));

        Render render = new Render().setMultithreading(3).setAdaptiveSampleFlag(true).setDebugPrint() //
                .setImageWriter(new ImageWriter("MiniProject2", 1000, 1000)) //
                .setCamera(camera) //
                .setRayTracer(new BasicRayTracer(scene).setFlagSoftShadows(true));
        render.renderImage();
        render.writeToImage();

        long end=java.lang.System.nanoTime()-time;
        System.out.println(end/1000000000);
    }

    @Test
    public void Pyramid() {
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

        Render render = new Render().setMultithreading(3).setAdaptiveSampleFlag(false).setDebugPrint()
                .setImageWriter(new ImageWriter("Pyramid", 600, 600)) //
                .setCamera(camera) //
                .setRayTracer(new BasicRayTracer(scene).setFlagSoftShadows(false));
        render.renderImage();
        render.writeToImage();

        long end=java.lang.System.nanoTime()-time;
        System.out.println(end/1000000000);
    }


}
