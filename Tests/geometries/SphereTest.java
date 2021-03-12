package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============

        Point3D point=new Point3D(1,2,3);
        Sphere sphere=new Sphere(new Point3D(1,1,1),1);
        assertEquals(new Vector(0,1/Math.sqrt(5),2/Math.sqrt(5)),sphere.getNormal(point),"ERROR:Bad normal to sphere");
    }
}