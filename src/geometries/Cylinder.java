package geometries;

import primitives.Ray;


/**
 * Class Cylinder is the class representing a Cylinder for Cartesian
 * coordinate system.
 */
public class Cylinder extends Tube{
    double height;

    /**
     *  ctor
     * @param axisRay the central axis ray
     * @param radius radius
     * @param height height of Cylinder
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

    /**
     *
     * @return height of Cylinder
     */
    public double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Cylinder:" +
                "height=" + height +
                ", " + super.toString();
    }
}
