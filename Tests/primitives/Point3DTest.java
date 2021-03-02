package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Point3DTest {

    Point3D p1=new Point3D(1.0d,2.0d,3.0d);
    Point3D p2=new Point3D(1,2,3);
    @Test
    void testEquals() {
        boolean qeuality=p1.equals(p2);
        assertTrue(qeuality);
    }

    @Test
    void distanceSquared() {
        Point3D p3=new Point3D(-4,-2.5,-6);
        double result =p3.distanceSquared(p1);
        System.out.println(result);
       assertEquals(126.35,result,0.2);
    }
}