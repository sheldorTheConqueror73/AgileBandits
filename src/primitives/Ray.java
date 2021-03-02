package primitives;

import java.util.Objects;

public class Ray {
    Point3D p0;
    Vector dir;

    public Ray(Point3D _p0, Vector _dir) {
        this.p0=_p0;
        this.dir=_dir.normalize();
    }

    public Point3D getP0() {
        return new Point3D(p0.x,p0.y,p0.z);
    }

    public Vector getDir() {
        return new Vector(dir.head);
    }

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
}
