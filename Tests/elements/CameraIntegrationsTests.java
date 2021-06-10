
package elements;


import geometries.*;
import primitives.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;


public class CameraIntegrationsTests {

    /**
     *
     * @param camera camera
     * @param inter the intersected geometry (grandson of Intersectable Interface)
     * @param expected the expected intersections
     * @param testName the name of the current test
     */
    void testViewPlane(Camera camera, Intersectable inter,int expected,String testName){
        int count=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                //List<Point3D> result=inter.findIntersections(camera.constructRayThroughPixel(3,3,j,i));
               // count+= result!=null? result.size() : 0;
            }
        }
        assertEquals(expected,count,"test "+testName+" failed, wrong number pf intersections\n");
    }
    Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)).setDistance(1).setViewPlaneSize(3,3);
    Camera camera2 = new Camera(new Point3D(0,0,0.5), new Vector(0, 0, -1), new Vector(0, 1, 0)).setDistance(1).setViewPlaneSize(3,3);

    /**
     * integration with camera and plane
     */
    @Test
    void integrationsCameraPlane() {
        //Plane intersections:
        //EP1 9 intersections
       testViewPlane(camera, new Plane(new Point3D(0,0,-6),new Vector(0,0,1)),9,"Plane EP1");

       //EP2 9 intersections
        testViewPlane(camera, new Plane(new Point3D(0,0,-6),new Vector(0,1,2)),9,"Plane EP2");
        //EP3 6 intersections
       testViewPlane(camera, new Plane(new Point3D(0,0,-6),new Vector(0,1,1)),6,"Plane EP3");
        //EP4 0 intersections
        testViewPlane(camera, new Plane(new Point3D(0,0,6),new Vector(0,0,1)),0,"Plane EP4");
    }
    /**
     * integration with camera and sphere
     */
    @Test
    void integrationsCameraSphere(){
        //Sphere intersections:
        //EP1 2 intersections
        testViewPlane(camera,new Sphere(1,new Point3D(0,0,-3)),2,"Sphere EP1");
        //EP2 18 intersections
        testViewPlane(camera2,new Sphere(2.5,new Point3D(0,0,-2.5)),18,"Sphere EP2");

        //EP3 10 intersections
        testViewPlane(camera2,new Sphere(2,new Point3D(0,0,-2)),10,"Sphere EP3");
        //EP4 9 intersections
        testViewPlane(camera,new Sphere(4,new Point3D(0,0,-1)),9,"Sphere EP4");
        //EP5 0 intersections
        testViewPlane(camera,new Sphere(0.5,new Point3D(0,0,1)),0,"Sphere EP5");

    }
    /**
     * integration with camera and triangle
     */
    @Test
    void integrationsCameraTriangle(){
        //Triangle intersections:
        //EP1 1 intersections
        testViewPlane(camera,new Triangle(new Point3D(0,1,-2), new Point3D(1,-1,-2), new Point3D(-1,-1,-2)),1,"Triangle EP1");
        //EP2 2 intersections
        testViewPlane(camera,new Triangle(new Point3D(0,20,-2), new Point3D(1,-1,-2), new Point3D(-1,-1,-2)),2,"Triangle EP2");

    }

}

