package geometries;

import primitives.Point3D;
import primitives.Vector;
/**
 * Class Sphere is the class representing a Sphere for Cartesian
 * coordinate system.
 */
public class Sphere implements Geometry{

    Point3D center;
    double radius;

    /**
     * ctor
     * @param center center of the sphere
     * @param radius radius of the sphere
     */
    public Sphere(Point3D center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    /**
     *
     * @return center
     */
    public Point3D getCenter() {
        return center;
    }

    /**
     *
     * @return radius
     */
    public double getRadius() {
        return radius;
    }

    @Override
    public Vector getNormal(Point3D point) {
        return (point.subtract(center)).normalize();
    }

    @Override
    public String toString() {
        return "Sphere:" +
                "center=" + center +
                ", radius=" + radius ;
    }
}
