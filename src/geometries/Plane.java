package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * Class Plane is the class representing a Plane for Cartesian
 * coordinate system.
 */
public class Plane implements Geometry{
    Point3D q0;
    Vector normal;

    /**
     * ctor
     * @param p1 1st plane point
     * @param p2 2nd plane point
     * @param p3 3rd plane point
     */
    public Plane(Point3D p1,Point3D p2,Point3D p3){
        this.q0=p1;
        normal=null;
    }

    /**
     * ctor
     * @param p plane point
     * @param vector normal vector
     */
    public Plane(Point3D p,Vector vector){
        this.q0=p;
        this.normal=vector;
    }

    /**
     *
     * @return returns p0
     */
    public Point3D getQ0() {
        return q0;
    }
    /**
     *
     * @return returns normal
     */
    public Vector getNormal() {
        return normal;
    }

    @Override
    public String toString() {
        return "Plane:" +
                "q0=" + q0 +
                ", normal=" + normal ;
    }

    @Override
    public Vector getNormal(Point3D point) {
        return null;
    }
}
