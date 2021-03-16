package primitives;

import java.util.Objects;

/**
 * Class ray is the class representing a ray for Cartesian
 * coordinate system.
 */
public class Ray {
    Point3D p0;
    Vector dir;

    /**
     *
     * @param _p0  starting point
     * @param _dir direction vector
     */
    public Ray(Point3D _p0, Vector _dir) {
        this.p0=_p0;
        this.dir=_dir.normalize();
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
}
