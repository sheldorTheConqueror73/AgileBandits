package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Plane implements Geometry{
    Point3D q0;
    Vector normal;

    public Plane(Point3D p1,Point3D p2,Point3D p3){
        this.q0=p1;
        normal=null;
    }

    public Plane(Point3D p,Vector vector){
        this.q0=p;
        this.normal=vector;
    }

    public Point3D getQ0() {
        return q0;
    }

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
        return normal;
    }
}
