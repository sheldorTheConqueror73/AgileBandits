package primitives;

import java.util.Objects;

/**
 *
 */
public class Point3D {
    Coordinate x;
    Coordinate y;
    Coordinate z;

    public static Point3D ZERO=new Point3D(0,0,0);
    /**
     *
     * @param _x coordinate for x axis
     * @param _y coordinate for y axis
     * @param _z coordinate for z axis
     */
    public Point3D(Coordinate _x, Coordinate _y, Coordinate _z) {
        this(_x.coord,_y.coord,_z.coord);
    }

    public Point3D(double _x, double _y, double _z) {
        this.x = new Coordinate(_x);
        this.y = new Coordinate(_y);
        this.z = new Coordinate(_z);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point3D point3D = (Point3D) o;
        return x.equals(point3D.x) && y.equals(point3D.y) && z.equals(point3D.z);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x.coord, y.coord, z.coord);
    }

    public double distanceSquared(Point3D otherPoint){
        return ((otherPoint.x.coord-x.coord)*(otherPoint.x.coord-x.coord))+
                ((otherPoint.y.coord-y.coord)*(otherPoint.y.coord-y.coord))+
                ((otherPoint.z.coord-z.coord)*(otherPoint.z.coord-z.coord));
    }

    public double distance(Point3D otherPoint){
        return Math.sqrt(distanceSquared(otherPoint));
    }

    public Point3D add(Vector vector){
        return new Point3D(x.coord+vector.head.x.coord,
                           y.coord+vector.head.y.coord,
                           z.coord+vector.head.z.coord);
    }

    public Vector subtract(Point3D otherPoint){
        return new Vector(new Point3D(this.x.coord-otherPoint.x.coord,this.y.coord-otherPoint.y.coord,this.z.coord-otherPoint.z.coord));
    }

    @Override
    public String toString() {
        return "Point3D:" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '.';
    }
}
