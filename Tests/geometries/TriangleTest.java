package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

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
}