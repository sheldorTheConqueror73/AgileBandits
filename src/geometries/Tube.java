package geometries;

import java.util.LinkedList;
import java.util.List;
import primitives.*;
import static primitives.Util.*;
/**
 * Class Tube is the class representing a tube for Cartesian
 * coordinate system.
 */
public class Tube extends Geometry{
    Ray axisRay;
    final protected double _radius;

    /**
     * ctor
     * @param axisRay central axis ray
     * @param radius tube radius
     */
    public Tube(Ray axisRay, double radius) {
        this._radius=radius;
        this.axisRay = axisRay;
     }

    /**
     *
     * @return axisRay
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    public double get_radius() {
        return _radius;
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
                ", radius=" + _radius ;
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        var reslut=this.findIntersections(ray);
        List<GeoPoint> intersections = new LinkedList<GeoPoint>();
        if(reslut==null)
            return null;
        for ( var item: reslut) {
            intersections.add(new GeoPoint(this,item));
        }
        return intersections;
    }
}
