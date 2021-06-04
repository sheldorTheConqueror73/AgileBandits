package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class PyramidTest {

    @Test
    void findGeoIntersections() {
    }

    @Test
    void getNormal() {
        Pyramid pyramid=new Pyramid(new Rectangle(new Point3D(0,3,0),new Point3D(2,3,0),new Point3D(2,1,0),new Point3D(0,1,0)),new Point3D(1,2,2));
        Ray ray=new Ray(new Point3D(6,2,2),new Vector(-4.31,-0.14,-1.37));
        Triangle tr=new Triangle(new Point3D(2,3,0),new Point3D(2,1,0),new Point3D(1,2,2));
        List<Intersectable.GeoPoint> gp=tr.findGeoIntersections(ray);
        assertTrue(tr.getNormal(gp.get(0).point).equals(pyramid.getNormal(gp.get(0).point)),"bad normal");

        Ray ray2=new Ray(new Point3D(5.74,-0.19,0),new Vector(-1.74,0.19,0));
        gp=tr.findGeoIntersections(ray2);
        assertEquals(null,gp,"bad intersection");
    }
}