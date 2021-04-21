package primitives;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    @Test
    void getClosestPointTest() {
        Ray ray=new Ray(Point3D.getZERO(),new Vector(0,0,1));
        List<Point3D> points=new LinkedList<>();
        //EP1
        points.add(new Point3D(-100,0,45));
        points.add(new Point3D(0,200,1));
        points.add(new Point3D(-63,0,100));
        points.add(new Point3D(0,0,40));
        points.add(new Point3D(0,80,8));
        assertEquals(points.get(3),ray.getClosestPoint(points),"EP1");


        //BVA1
         points=null;
        assertNull(ray.getClosestPoint(points),"BVA1");

        //BVA2
        points=new LinkedList<>();
        points.add(new Point3D(0,0,40));
        points.add(new Point3D(-100,0,45));
        points.add(new Point3D(0,200,1));
        points.add(new Point3D(-63,0,100));
        points.add(new Point3D(0,80,8));
        assertEquals(points.get(0),ray.getClosestPoint(points),"BVA2");

        //BVA3
        points=new LinkedList<>();
        points.add(new Point3D(-100,0,45));
        points.add(new Point3D(0,200,1));
        points.add(new Point3D(-63,0,100));
        points.add(new Point3D(0,80,8));
        points.add(new Point3D(0,0,40));
        assertEquals(points.get(4),ray.getClosestPoint(points),"BVA3");
    }
}