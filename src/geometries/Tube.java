package geometries;

import primitives.*;

/**
 * Class Tube is the class representing a tube for Cartesian
 * coordinate system.
 */
public class Tube implements Geometry{
    Ray axisRay;
    double radius;

    /**
     * ctor
     * @param axisRay central axis ray
     * @param radius tube radius
     */
    public Tube(Ray axisRay, double radius) {
        this.axisRay = axisRay;
        this.radius = radius;
    }

    /**
     *
     * @return axisRay
     */
    public Ray getAxisRay() {
        return axisRay;
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
        return null;
    }

    @Override
    public String toString() {
        return "Tube:" +
                "axisRay=" + axisRay +
                ", radius=" + radius ;
    }
}
