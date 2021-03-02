package geometries;

import primitives.Ray;

public class Cylinder extends Tube{
    double height;

    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

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
