package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.isZero;

/**
 * Class Plane is the class representing a Plane for Cartesian
 * coordinate system.
 */
public class Plane implements Geometry {
    Point3D q0;
    Vector normal;

    /**
     * ctor
     *
     * @param p1 1st plane point
     * @param p2 2nd plane point
     * @param p3 3rd plane point
     */
    public Plane(Point3D p1, Point3D p2, Point3D p3) {
        this.q0 = p1;
        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p1);
        normal = (v1.crossProduct(v2)).normalize();
    }

    /**
     * ctor
     *
     * @param p      plane point
     * @param vector normal vector
     */
    public Plane(Point3D p, Vector vector) {
        this.q0 = p;
        this.normal = vector.normalized();
    }

    /**
     * @return returns p0
     */
    public Point3D getQ0() {
        return q0;
    }

    /**
     * @return returns normal
     */
    public Vector getNormal() {
        // return getNormal(null);
        return normal;
    }

    @Override
    public String toString() {
        return "Plane:" +
                "q0=" + q0 +
                ", normal=" + normal;
    }

    @Override
    public Vector getNormal(Point3D point) {
        return normal;
    }

    /**
     * finds intersections with plane and ray
     * @param ray ray to intersect
     * @return a list of intersection points or null if there are none
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        Point3D p0 = ray.getP0();

        Vector v = ray.getDir();

        if (q0.equals(p0)) {
            return List.of(q0);
        }
        double nv = normal.dotProduct(v);

        // the ray is lying on the plane
        if (isZero(nv)) {
            return null;
        }
        double t = normal.dotProduct(q0.subtract(p0));
        t /= nv;
        if(t<=0){
            return null;
        }
        Point3D p = ray.getTargetPoint(t);
        return List.of(p);
    }
}
