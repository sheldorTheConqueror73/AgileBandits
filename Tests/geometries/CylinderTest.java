package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class CylinderTest {


    @Test
    void getNormal() {
        Ray axisRay=new Ray(
                new Point3D(1,0,0),
                new Vector(0,1,0));
        Cylinder cylinder =new Cylinder(axisRay,1,8);
        assertEquals(new Vector(1,0,0),cylinder.getNormal(new Point3D(2,1,0)),"ERROR:");
    }
}