package primitives;


import java.util.Objects;

import static primitives.Point3D.ZERO;

/**
 * Class vector is the class representing a vector for Cartesian
 * coordinate system.
 */
public class Vector {

    Point3D head;


    /**
     * ctor
     * @param head the vector head
     */
    public Vector(Point3D head) {
        if(head.equals(ZERO)){
            throw new IllegalArgumentException("head cannot be point (0,0,0)");
        }
        this.head =new Point3D(head.x, head.y, head.z);
    }

    /**
     *
     * @return a new point representing head
     */
    public Point3D getHead() {
        return new Point3D(head.x, head.y, head.z);
    }

    /**
     * ctor
     * @param x x coordinate for x axis
     * @param y y coordinate for y axis
     * @param z z coordinate for z axis
     */
    public Vector(Coordinate x,Coordinate y,Coordinate z){
        this(new Point3D(x,y,z));
    }

    /**
     *ctor
     * @param x x coordinate for x axis
     * @param y y coordinate for y axis
     * @param z z coordinate for z axis
     */
    public Vector(double x,double y,double z){
        this(new Point3D(x,y,z));
    }

    /**
     * adds two vectors
     * @param vector second vector
     * @return vector1+vector2
     */
    public Vector add(Vector vector){
        return new Vector(head.add(vector));
    }
    /**
     * subtracts two vectors
     * @param vector second vector
     * @return vector1-vector2
     */
    public Vector subtract(Vector vector){
        return head.subtract(vector.head);
    }

    /**
     * scales vector by K
     * @param k sacle factor
     * @return vector scaled by k
     */
    public Vector scale(double k){
        if(k==0d){
            throw  new IllegalArgumentException("scale cannot be zero");
        }
        return new Vector(head.x.coord*k,head.y.coord*k,head.z.coord*k);
    }

    /**
     *
     * @param vector second vector
     * @return dot product of the two vectors
     */
    public double dotProduct(Vector vector){
        return  this.head.x.coord*vector.head.x.coord+
                this.head.y.coord*vector.head.y.coord+
                this.head.z.coord*vector.head.z.coord;
    }
    /**
     *
     * @param vector second vector
     * @return cross product of the two vectors
     */
    public Vector crossProduct(Vector vector){
        return new Vector(this.head.y.coord*vector.head.z.coord-this.head.z.coord*vector.head.y.coord,
                          this.head.z.coord*vector.head.x.coord-this.head.x.coord*vector.head.z.coord,
                          this.head.x.coord*vector.head.y.coord-this.head.y.coord*vector.head.x.coord);
    }

    /**
     *
     * @return the length of the vector squared
     */
    public double lengthSquared(){
        return (this.head.x.coord*this.head.x.coord+this.head.y.coord*this.head.y.coord+this.head.z.coord*this.head.z.coord);
    }
    /**
     *
     * @return the length of the vector
     */
    public  double length(){
        return Math.sqrt(lengthSquared());
    }

    /**
     * normalizes vector
     * @return this (for chaining operators)
     */
    public Vector normalize(){
       this.head=(scale(1/length()).head);
       return this;
    }
    /**
     * normalizes vector
     * @return  new normalized vector
     */
    public Vector normalized(){
        Vector v=new Vector(this.head);
       return new Vector(v.normalize().head);
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
        Vector vector = (Vector) o;
        return head.equals(vector.head);
    }


    @Override
    public String toString() {
        return "Vector:" +
                "head=" + head ;
    }
    public Vector getOrthogonal() {
        /*if(Util.isZero(_head._x._coord)) {
            return new Vector(0,_head._z._coord*-1,_head._y._coord).normalize();
        }
        return new Vector(_head._y._coord*-1,_head._x._coord,0).normalize();*/
        if (head.x.coord <= head.y.coord && head.x.coord <= head.z.coord)
            return new Vector(0.0, head.z.coord * -1, head.y.coord).normalize();
        else if (head.y.coord <= head.x.coord && head.y.coord <= head.z.coord)
            return new Vector(head.z.coord * -1, 0.0, head.x.coord).normalize();
        else if (head.x.coord == 0 && head.y.coord == 0)
            return new Vector(1.0, 1.0, 0.0).normalize();
        else
            return new Vector(head.y.coord * -1, head.x.coord, 0.0).normalize();
    }

}
