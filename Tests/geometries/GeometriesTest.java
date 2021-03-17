package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {

    @Test
    void findIntersections() {
        //declaring geometries: rectangle,triangle,Sphere
        Polygon rectangle=new Polygon(
                new Point3D(-4,0,6),
                new Point3D(0,4,6),
                new Point3D(0,4,-1.2),
                new Point3D(-4,0,-1.2)
        );

        Triangle triangle=new Triangle(
                new Point3D(3.9,-2.9,1.5),
                new Point3D(5.3,-8.3,-1.2),
                new Point3D(9,-3.8,-1.2)
        );

        Sphere sphere=new Sphere(2,new Point3D(2,0,0));

        Geometries geometries=new Geometries(rectangle,sphere,triangle);
        //BVA cross all geometries
        List<Point3D> result=geometries.findIntersections(
                new Ray(
                        new Point3D(9.55,-7.64,0),
                        new Vector(-19.55,17.64,0)
                )
        );

        assertEquals(4,result.size(),"BVA: cross all geometries");

        //BVA cross only one geometry
        result=geometries.findIntersections(
                new Ray(
                        new Point3D(12.49,-3.67,0),
                        new Vector(-10.49,-0.33,0)
                )
        );

        assertEquals(1,result.size(),"BVA: cross only one geometry");

        //BVA does not intersect
        result=geometries.findIntersections(
                new Ray(
                        new Point3D(8.67,3.08,0),
                        new Vector(-6.67,-7.08,0)
                )
        );

        assertEquals(null,result,"BVA: does not intersect");


        //EP cross part of geometries
        result=geometries.findIntersections(
                new Ray(
                        new Point3D(5.86,-1.39,1),
                        new Vector(-9.65,3.94,1)
                )
        );

        assertEquals(3,result.size(),"EP cross part of geometries");

        Geometries emptyGeometries=new Geometries();
        result=emptyGeometries.findIntersections(
                new Ray(
                        new Point3D(8.67,3.08,0),
                        new Vector(-6.67,-7.08,0)
                )
        );
        assertEquals(null,result,"BVA: empty collection");

    }
}