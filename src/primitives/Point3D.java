package primitives;

import java.util.Objects;

/**
 *Class Point3D is the class representing a 3D point for Cartesian
 *  coordinate system.
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

    /**
     * return x coordinate
     * @return
     */
    public double getX() {
        return x.coord;
    }

    /**
     * return y coordinate
     * @return
     */
    public double getY() {
        return y.coord;
    }

    /**
     * return z coordinate
     * @return
     */
    public double getZ() {
        return z.coord;
    }

    /**
     * return zero point
     * @return
     */
    public static Point3D getZERO() {
        return ZERO;
    }

    /**
     *
     * @param _x coordinate for x axis
     * @param _y coordinate for y axis
     * @param _z coordinate for z axis
     */
    public Point3D(double _x, double _y, double _z) {
        this.x = new Coordinate(_x);
        this.y = new Coordinate(_y);
        this.z = new Coordinate(_z);
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
        Point3D point3D = (Point3D) o;
        return x.equals(point3D.x) && y.equals(point3D.y) && z.equals(point3D.z);
    }

    /**
     *
     * @param otherPoint another point
     * @return distance squared from second point
     */

    public double distanceSquared(Point3D otherPoint){
        return ((otherPoint.x.coord-x.coord)*(otherPoint.x.coord-x.coord))+
                ((otherPoint.y.coord-y.coord)*(otherPoint.y.coord-y.coord))+
                ((otherPoint.z.coord-z.coord)*(otherPoint.z.coord-z.coord));
    }

    /**
     *
     * @param otherPoint another point
     * @return distance from second point
     */
    public double distance(Point3D otherPoint){
        return Math.sqrt(distanceSquared(otherPoint));
    }

    /**
     *
     * @param vector another vector
     * @return adds a point to a vector and returns new point
     */
    public Point3D add(Vector vector){
        return new Point3D(x.coord+vector.head.x.coord,
                           y.coord+vector.head.y.coord,
                           z.coord+vector.head.z.coord);
    }

    /**
     *
     * @param otherPoint another point
     * @return subtracts a point from a vector and returns new vector
     */
    public Vector subtract(Point3D otherPoint){
        return new Vector(new Point3D(this.x.coord-otherPoint.x.coord,this.y.coord-otherPoint.y.coord,this.z.coord-otherPoint.z.coord));
    }

    /**
     * calc the middle of two points
     * @param p2 second point
     * @return middle
     */
    public Point3D middleOf(Point3D p2){
        double x=(this.x.coord+p2.x.coord)/2;
        double y=(this.y.coord+p2.y.coord)/2;
        double z=(this.z.coord+p2.z.coord)/2;
        return new Point3D(x,y,z);
    }

    /**
     * print the point
     * @return
     */
    @Override
    public String toString() {
        return "Point3D:" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '.';
    }
}
