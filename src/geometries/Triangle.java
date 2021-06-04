package geometries;

import primitives.*;

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

    public double area(){
        Vector ab=vertices.get(1).subtract(vertices.get(0));
        Vector ac=vertices.get(2).subtract(vertices.get(0));
        double ablen=ab.length();
        double aclen=ac.length();
        double cosAngle=(ab.dotProduct(ac))/(ablen*aclen);
        cosAngle=Math.acos(cosAngle);
        return 0.5*ablen*aclen*Math.sin(cosAngle);
    }

    public static double area(Point3D p1,Point3D p2,Point3D p3){
        Vector ab=p2.subtract(p1);
        Vector ac=p3.subtract(p1);
        double ablen=ab.length();
        double aclen=ac.length();
        double cosAngle=(ab.dotProduct(ac))/(ablen*aclen);
        cosAngle=Math.acos(cosAngle);
        return 0.5*ablen*aclen*Math.sin(cosAngle);
    }

    @Override
    public String toString() {
        return "Triangle:" + super.toString();
    }
}
