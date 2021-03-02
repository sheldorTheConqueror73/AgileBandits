package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Point3DTest {

    Point3D p1=new Point3D(1,2,3);
    Point3D p2=new Point3D(1.0,2.0,3.0);
    Point3D p3=new Point3D(-1,-2,-3);
    Point3D p4=new Point3D(-1,-2,-3);
    @Test
    void testEquals() {
        assertTrue(p1.equals(p2));
        assertTrue(p1.equals(p1));
        assertTrue(p3.equals(p4));
        assertFalse(p3.equals(p1));
    }

    @Test
    void distanceSquared() {
        Point3D p3=new Point3D(-4,-2.5,-6);
        double result =p3.distanceSquared(p1);
        System.out.println(result);
       assertEquals(126.35,result,0.2);
    }

    @Test
    void subtract() {

    }
}