package geometries;

import primitives.Point3D;

public class Rectangle extends Polygon{
    public Rectangle(Point3D p1, Point3D p2, Point3D p3,Point3D p4){
        super(p1,p2,p3,p4);
    }

    public double area(){
       return vertices.get(0).distance(vertices.get(1))*vertices.get(0).distance(vertices.get(3));
    }
}
