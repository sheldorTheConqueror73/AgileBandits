package geometries;

import primitives.Point3D;

/**
 * Class Triangle is the class representing a Triangle for Cartesian
 * coordinate system.
 */
public class Triangle extends Polygon{
    /**
     * ctor
     * @param p1 point
     * @param p2 point
     * @param p3 point
     */
    public Triangle(Point3D p1,Point3D p2,Point3D p3){
        super(p1,p2,p3);
    }

    @Override
    public String toString() {
        return "Triangle:" + super.toString();
    }
}
