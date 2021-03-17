package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============

        Point3D point = new Point3D(1, 2, 3);
        Sphere sphere = new Sphere(1, new Point3D(1, 1, 1));
        assertEquals(new Vector(0, 1 / Math.sqrt(5), 2 / Math.sqrt(5)), sphere.getNormal(point), "ERROR:Bad normal to sphere");
    }

    @Test
    void findIntersections() {
        Sphere sphere = new Sphere(1d, new Point3D(1, 0, 0));

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertEquals(null,
                sphere.findIntersections(
                        new Ray(
                                new Point3D(-1, 0, 0),
                                new Vector(1, 1, 0))),
                "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
        Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
        List<Point3D> result = sphere.findIntersections(
                new Ray(
                        new Point3D(-1, 0, 0),
                        new Vector(3, 1, 0)
                )
        );

        assertEquals(2, result.size(), "Wrong number of points");

        if (result.get(0).getX() > result.get(1).getX())
             result = List.of(result.get(1), result.get(0));

        assertEquals(List.of(p1, p2), result, "Ray crosses sphere");

        // TC03: Ray starts inside the sphere (1 point)
        Ray r1 = new Ray(new Point3D(1,0.5,0.5), new Vector(-0.03,-0.64,0.49));
        result = sphere.findIntersections(r1);
        assertTrue(result.size()==1,"Error size, TC03");
        assertTrue(result.get(0).equals(new Point3D( 0.9700156404665422,-0.13966633671376705,0.9897445390464779)),"Error wrong point, TC03");


        // TC04: Ray starts after the sphere (0 points)
        r1 = new Ray(new Point3D(0.07,-1,0), new Vector(-0.57,-0.5,0));
        result= sphere.findIntersections(r1);
        assertEquals(null,result,"Error wrong point, TC04");


        // =============== Boundary Values Tests ==================

        // ** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        r1= new Ray(new Point3D(1,-0.8,0.6), new Vector(0,1.8,-0.6));
        result= sphere.findIntersections(r1);
        assertEquals(result, List.of(new Point3D(1,1,0)),"Error wrong point, TC11");

        // TC12: Ray starts at sphere and goes outside (0 points)
        r1= new Ray(new Point3D(1,-0.8,0.6), new Vector(0,0,3));
        result= sphere.findIntersections(r1);
        assertEquals(null,result,"Error there should be no intersections, TC12");

        // ** Group: Ray's line goes through the center

        // TC13: Ray starts before the sphere (2 points)
        r1= new Ray(new Point3D(2,1,0), new Vector(-1,-2,0));
        result= sphere.findIntersections(r1);
        assertEquals(result,List.of(new Point3D(1,-1,0),new Point3D(1.8,0.6,0)),"Error wrong points, TC13");


        // TC14: Ray starts at sphere and goes inside (1 points)
        result = sphere.findIntersections(
                new Ray(
                        new Point3D(1, -1, 0),
                        new Vector(0, 2, 0)
                )
        );

        assertEquals(List.of(new Point3D(1,1,0)), result, "BVA:Ray starts at sphere and goes inside ");

        // TC15: Ray starts inside (1 points)
        result = sphere.findIntersections(
                new Ray(
                        new Point3D(1.29, 0.13, 0.26),
                        new Vector(0.42, 0.19, 0.37)
                )
        );

        assertEquals(List.of(new Point3D(1.708793099341218,0.31945402113055094,0.6289367779910728)), result, "BVA:Ray starts inside");

        // TC16: Ray starts at the center (1 points)
       result = sphere.findIntersections(
                new Ray(
                        new Point3D(1, 0, 0),
                        new Vector(0.71, 0.32, 0.63)
                )
        );
    // to fix----------------------------------------
        assertEquals(List.of(new Point3D(1.7087960691552895,0.3194573832812572,0.6289317233349752)), result, "BVA:Ray starts at the center");

        // TC17: Ray starts at sphere and goes outside (0 points)
        result = sphere.findIntersections(
                new Ray(
                        new Point3D(2, 0, 0),
                        new Vector(1.77, -1.12, 0)
                )
        );

        assertEquals(null, result, "BVA: Ray starts at sphere and goes outside");

        // TC18: Ray starts after sphere (0 points)
        result = sphere.findIntersections(
                new Ray(
                        new Point3D(1.83, -0.37, -0.43),
                        new Vector(1.95, -0.75, 0.43)
                )
        );

        assertEquals(null, result, "BVA:Ray starts after sphere");

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        result = sphere.findIntersections(
                new Ray(
                        new Point3D(2, -1.5, 0),
                        new Vector(0, 3.5, 0)
                )
        );

        assertEquals(null, result, "BVA:Ray starts before the tangent point");

        // TC20: Ray starts at the tangent point
        result = sphere.findIntersections(
                new Ray(
                        new Point3D(2, 0, 0),
                        new Vector(0, 2, 0)
                )
        );

        assertEquals(null, result, "BVA: Ray starts at the tangent point");
        // TC21: Ray starts after the tangent point
        result = sphere.findIntersections(
                new Ray(
                        new Point3D(2, 0.5, 0),
                        new Vector(0, 1.5, 0)
                )
        );

        assertEquals(null, result, "BVA:Ray starts after the tangent point");
        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        result = sphere.findIntersections(
                new Ray(
                        new Point3D(3, 0, 0),
                        new Vector(0, 2, 0)
                )
        );

        assertEquals(null, result, "BVA:Ray's line is outside, ray is orthogonal to ray start to sphere's center line");


    }
}