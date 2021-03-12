package geometries;

import java.util.List;
import primitives.*;
import static primitives.Util.*;
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

    /**
     * returns normal
     * @param point
     * @return
     */
    @Override
    public Vector getNormal(Point3D point) {
        Point3D po=axisRay.getP0();
        Vector v=axisRay.getDir();

        Vector vector=point.subtract(po);

        double projection=vector.dotProduct(v);

        if(!isZero(projection)){
            po= po.add(v.scale(projection));
        }

        Vector check=point.subtract(po);

        return check.normalize();
    }

    @Override
    public String toString() {
        return "Tube:" +
                "axisRay=" + axisRay +
                ", radius=" + radius ;
    }
}
