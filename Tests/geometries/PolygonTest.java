package geometries;

import org.junit.jupiter.api.Test;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Polygons
 *
 * @author Dan
 *
 */
public class PolygonTest {

    /**
     * Test method for
     * @link geometries.Polygon(primitives.Point3D, primitives.Point3D, primitives.Point3D, primitives.Point3D)}.
     */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct concave quadrangular with vertices in correct order
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(-1, 1, 1));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct polygon");
        }

        // TC02: Wrong vertices order
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(0, 1, 0),
                    new Point3D(1, 0, 0), new Point3D(-1, 1, 1));
            fail("Constructed a polygon with wrong order of vertices");
        } catch (IllegalArgumentException e) {}

        // TC03: Not in the same plane
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 2, 2));
            fail("Constructed a polygon with vertices that are not in the same plane");
        } catch (IllegalArgumentException e) {}

        // TC04: Concave quadrangular
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0.5, 0.25, 0.5));
            fail("Constructed a concave polygon");
        } catch (IllegalArgumentException e) {}

        // =============== Boundary Values Tests ==================

        // TC10: Vertex on a side of a quadrangular
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 0.5, 0.5));
            fail("Constructed a polygon with vertix on a side");
        } catch (IllegalArgumentException e) {}

        // TC11: Last point = first point
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 0, 1));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {}

        // TC12: Colocated points
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 1, 0));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {}

    }

    /**
     * Test method for {@link geometries.Polygon#getNormal(primitives.Point3D)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Polygon pl = new Polygon(
                new Point3D(0, 0, 1),
                new Point3D(1, 0, 0),
                new Point3D(0, 1, 0),
                new Point3D(-1, 1, 1));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals( new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point3D(0, 0, 1)),"Bad normal to trinagle");
    }

    @Test
    void findIntersections() {
        Polygon rectangle=new Polygon(new Point3D(2,4,1.5),new Point3D(6,4,1.5),new Point3D(6,2,1.5),new Point3D(2,2,1.5));

        List<Point3D> result = rectangle.findIntersections(
                new Ray(
                        new Point3D(3.67, 2.3, 0),
                        new Vector(0.13, 0.55, 1.5))
        );
       // assertEquals(1,result.size(),"ERROR:");
        // the right point
        //EP1
        assertEquals(List.of(new Point3D(3.8,2.85,1.5)),result,"ERROR:wrong point");

        result = rectangle.findIntersections(
                new Ray(
                        new Point3D(4, 3, 0),
                        new Vector(0, -4, 1.5))
        );
        //EP2
        assertEquals(null,result,"ERROR:");

        result = rectangle.findIntersections(
                new Ray(
                        new Point3D(3.79, 3.1, 0),
                        new Vector(-2.42, -1.54, 1.5))
        );
        //EP3
        assertEquals(null,result,"ERROR:");

        result = rectangle.findIntersections(
                new Ray(
                        new Point3D(4, 3, 0),
                        new Vector(2, -1, 1.5))
        );
        //BVA1 zero problem ------ to fix
        assertEquals(null,result,"ERROR:wrong point");

        result = rectangle.findIntersections(
                new Ray(
                        new Point3D(4, 3, 0),
                        new Vector(0, -1, 1.5))
        );
        //BVA2 zero problem ------ to fix
        assertEquals(null,result,"ERROR:wrong point");

        result = rectangle.findIntersections(
                new Ray(
                        new Point3D(4, 4, 0),
                        new Vector(-3, 0, 1.5))
        );
        //BVA3
         assertEquals(null,result,"ERROR:wrong point");

    }
    @Test
    void findGeoIntersections() {
        Polygon rectangle=new Polygon(new Point3D(2,4,1.5),new Point3D(6,4,1.5),new Point3D(6,2,1.5),new Point3D(2,2,1.5));

        List<Intersectable.GeoPoint> result = rectangle.findGeoIntersections(
                new Ray(
                        new Point3D(3.67, 2.3, 0),
                        new Vector(0.13, 0.55, 1.5))
        );
       // assertEquals(1,result.size(),"ERROR:");
        // the right point
        //EP1
        assertEquals(List.of(new Intersectable.GeoPoint(rectangle, new Point3D(3.8,2.85,1.5))),result,"ERROR:wrong point");

        result = rectangle.findGeoIntersections(
                new Ray(
                        new Point3D(4, 3, 0),
                        new Vector(0, -4, 1.5))
        );
        //EP2
        assertEquals(null,result,"ERROR:");

        result = rectangle.findGeoIntersections(
                new Ray(
                        new Point3D(3.79, 3.1, 0),
                        new Vector(-2.42, -1.54, 1.5))
        );
        //EP3
        assertEquals(null,result,"ERROR:");

        result = rectangle.findGeoIntersections(
                new Ray(
                        new Point3D(4, 3, 0),
                        new Vector(2, -1, 1.5))
        );
        //BVA1 zero problem ------ to fix
        assertEquals(null,result,"ERROR:wrong point");

        result = rectangle.findGeoIntersections(
                new Ray(
                        new Point3D(4, 3, 0),
                        new Vector(0, -1, 1.5))
        );
        //BVA2 zero problem ------ to fix
        assertEquals(null,result,"ERROR:wrong point");

        result = rectangle.findGeoIntersections(
                new Ray(
                        new Point3D(4, 4, 0),
                        new Vector(-3, 0, 1.5))
        );
        //BVA3
         assertEquals(null,result,"ERROR:wrong point");

    }
}
