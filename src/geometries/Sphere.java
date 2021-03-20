package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Class Sphere is the class representing a Sphere for Cartesian
 * coordinate system.
 */
public class Sphere extends RadialGeometry implements Geometry{

    Point3D center;

    /**
     * ctor
     * @param center center of the sphere
     * @param radius radius of the sphere
     */
    public Sphere(double radius,Point3D center) {
        super(radius);
        this.center = center;
    }

    /**
     *
     * @return center
     */
    public Point3D getCenter() {
        return center;
    }

    /**
     * returns normal
     * @param point
     * @return
     */
    @Override
    public Vector getNormal(Point3D point) {
        return (point.subtract(center)).normalize();
    }

    @Override
    public String toString() {
        return "Sphere:" +
                "center=" + center +
                ", radius=" + _radius ;
    }

    /**
     * finds intersections with sphere and ray
     * @param ray ray to intersect
     * @return a list of intersection points or null if there are none
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        Vector  u;
        double tm,d;
        if(center.equals(ray.getP0())){
            tm=0;
            d=0;
        }else {
            u = center.subtract(ray.getP0());


            Vector v = ray.getDir();
            tm = u.dotProduct(v);
            d = alignZero(Math.sqrt(u.lengthSquared() - tm * tm));
        }
       if(d> _radius){
           return null;
       }
       double th = alignZero(Math.sqrt(_radius*_radius - d*d));

       // P is on the surface of the sphere
       if(isZero(th)){
           return null;
       }

       double t1=alignZero(tm+th);
       double t2=alignZero(tm-th);

       if(t1>0&&t2>0){
           return List.of(ray.getTargetPoint(t1),ray.getTargetPoint(t2));
       }
       if(t1>0){
           return List.of(ray.getTargetPoint(t1));
       }
        if(t2>0){
            return List.of(ray.getTargetPoint(t2));
        }
        return null;

    }
}
