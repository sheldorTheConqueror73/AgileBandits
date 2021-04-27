package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    @Test

    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        Plane plane = new Plane(
                new Point3D(0, 0, 1),
                new Point3D(1, 0, 0),
                new Point3D(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals( new Vector(sqrt3, sqrt3, sqrt3), plane.getNormal(new Point3D(4,5,6)),"Bad normal to plane");
    }

    @Test
    void findIntersections() {
        Plane plane= new Plane(
                new Point3D(0,0,2 ),
               new Point3D(-2,0,0 ),
               new Point3D(0,-3, 0)

        );
        // ============ Equivalence Partitions Tests ==============
        // TC01: ray intersects
        Ray r1 = new Ray( new Point3D(2,0,0), new Vector(-2,0,2));
        var result=plane.findIntersections(r1);
        assertEquals(List.of(new Point3D(0,0,2)),result,"EP1: wrong point");

        //TC02:  ray does not intersect
         plane= new Plane(
                new Point3D(-2,0,0),
                new Point3D(0,0,2 ),
                new Point3D(2,0,0)

        );
        r1=new Ray(new Point3D(0,-4,0), new Vector(0.7,-3,0));
         result=plane.findIntersections(r1);
        assertEquals(null,result,"EP2: result should be null");

        //TC03:  ray does not intersect p0 start at the plane
        r1=new Ray(new Point3D(0,0,2), new Vector(0,4,-2));
        result=plane.findIntersections(r1);
        assertEquals(null,result,"BVA1: result should be null");


    }
    @Test
    void findGeoIntersections() {
        Plane plane= new Plane(
                new Point3D(0,0,2 ),
               new Point3D(-2,0,0 ),
               new Point3D(0,-3, 0)

        );
        // ============ Equivalence Partitions Tests ==============
        // TC01: ray intersects
        Ray r1 = new Ray( new Point3D(2,0,0), new Vector(-2,0,2));
        var result=plane.findGeoIntersections(r1);
        assertEquals(List.of(new Intersectable.GeoPoint(plane,new Point3D(0,0,2))),result,"EP1: wrong point");

        //TC02:  ray does not intersect
         plane= new Plane(
                new Point3D(-2,0,0),
                new Point3D(0,0,2 ),
                new Point3D(2,0,0)

        );
        r1=new Ray(new Point3D(0,-4,0), new Vector(0.7,-3,0));
         result=plane.findGeoIntersections(r1);
        assertEquals(null,result,"EP2: result should be null");

        //TC03:  ray does not intersect p0 start at the plane
        r1=new Ray(new Point3D(0,0,2), new Vector(0,4,-2));
        result=plane.findGeoIntersections(r1);
        assertEquals(null,result,"BVA1: result should be null");


    }
}