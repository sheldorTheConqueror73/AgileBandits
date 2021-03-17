package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============

        Triangle triangle = new Triangle(
                new Point3D(0, 0, 1),
                new Point3D(1, 0, 0),
                new Point3D(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals( new Vector(sqrt3, sqrt3, sqrt3), triangle.getNormal(new Point3D(4,5,6)),"Bad normal to triangle");
    }

    @Test
    void findIntersections() {
        Polygon triangle = new Polygon(new Point3D(1, 4, 1.5), new Point3D(4, 2, 1), new Point3D(1.6, 1.2, 0));

        List<Point3D> result = triangle.findIntersections(
                new Ray(
                        new Point3D(2.28, 2.42, 0),
                        new Vector(-0.28, -0.55, 0.48))
        );
        //EP1
        assertEquals(List.of(new Point3D(2.000090119085935,1.8701770196330867,0.47984551013839705)),result,"ERROR:wrong point(EP:Inside polygon)");

        result = triangle.findIntersections(
                new Ray(
                        new Point3D(2.46, 2.7, 0),
                        new Vector(-1.66, -0.26, 0.26))
        );
        //EP2
        assertEquals(null,result,"ERROR:");

        result = triangle.findIntersections(
                new Ray(
                        new Point3D(2.46, 2.7, 0),
                        new Vector(-1.66, -0.26, 0.26))
        );
        //EP2
        assertEquals(null,result,"ERROR:");
    }
}