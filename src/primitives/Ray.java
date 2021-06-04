package primitives;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import geometries.Intersectable.GeoPoint;

/**
 * Class ray is the class representing a ray for Cartesian
 * coordinate system.
 */
public class Ray {
    private final Point3D p0;
    private final Vector dir;
    private static final double DELTA = 0.1;

    /**
     *
     * @param _p0  starting point
     * @param _dir direction vector
     */
    public Ray(Point3D _p0, Vector _dir) {
        this.p0=_p0;
        this.dir=_dir.normalize();
    }

    public Ray(Point3D _p0, Vector _dir, Vector n) {
        double sign = _dir.dotProduct(n);
        if (Util.isZero(sign)) {
            throw new IllegalArgumentException("direction*normal is zero");
        } else {
            this.p0 = _p0.add(n.scale(DELTA * Math.signum(sign)));
            this.dir = _dir;
        }
    }

    /**
     *
     * @return a new point representing p0
     */
    public Point3D getP0() {
        return new Point3D(p0.x,p0.y,p0.z);
    }

    /**
     *
     * @return a new vector representing dir
     */
    public Vector getDir() {
        return new Vector(dir.head);
    }

    public Point3D getClosestPoint(List<Point3D> points){
        Point3D minP=null;
        if(points==null)
            return null;
        double distance=Double.POSITIVE_INFINITY;
        for(Point3D p :points){
            double val=p.distance(p0);
            if(val<distance){
                distance=val;
                minP=p;
            }
        }
        return minP;
    }

    /**
     *
     * @param o object to compare to
     * @return true if b oth objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    @Override
    public String toString() {
        return "Ray:" +
                "p0=" + p0 +
                ", dir=" + dir;
    }

    public Point3D getTargetPoint(double t) {
        return p0.add(dir.scale(t));
    }


   public GeoPoint getClosestGeoPoint(List<GeoPoint> intersections){
       GeoPoint minP=null;
       if(intersections==null)
           return null;
       double distance=Double.POSITIVE_INFINITY;
       for(var p :intersections){
           double val=p.point.distance(p0);
           if(val<distance){
               distance=val;
               minP=p;
           }
       }
       return minP;
   }

   public List<Ray> createBeam(Ray ray,Point3D p,int amount,double radius){
        List<Ray> rays=new LinkedList<>();
        rays.add(ray);
        return null;
   }
}
