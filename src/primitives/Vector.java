package primitives;


import java.util.Objects;

import static primitives.Point3D.ZERO;

public class Vector {

    Point3D head;



    public Vector(Point3D head) {
        if(head.equals(ZERO)){
            throw new IllegalArgumentException("head cannot be point (0,0,0)");
        }
        this.head = head;
    }

    public Point3D getHead() {
        return new Point3D(head.x, head.y, head.z);
    }

    public Vector(Coordinate x,Coordinate y,Coordinate z){
        this(new Point3D(x,y,z));
    }

    public Vector(double x,double y,double z){
        this(new Point3D(x,y,z));
    }

    public Vector add(Vector vector){
        return new Vector(head.add(vector));
    }

    public Vector subtract(Vector vector){
        return head.subtract(vector.head);
    }

    public Vector scale(double k){
        return new Vector(head.x.coord*k,head.y.coord*k,head.z.coord*k);
    }

    public double dotProduct(Vector vector){
        return  this.head.x.coord*vector.head.x.coord+
                this.head.y.coord*vector.head.y.coord+
                this.head.z.coord*vector.head.z.coord;
    }

    public Vector crossProduct(Vector vector){
        return new Vector(this.head.y.coord*vector.head.z.coord-this.head.z.coord*vector.head.y.coord,
                          this.head.z.coord*vector.head.x.coord-this.head.x.coord*vector.head.z.coord,
                          this.head.x.coord*vector.head.y.coord-this.head.y.coord*vector.head.x.coord);
    }

    public double lengthSquared(){
        return (this.head.x.coord*this.head.x.coord+this.head.y.coord*this.head.y.coord+this.head.z.coord*this.head.z.coord);
    }

    public  double length(){
        return Math.sqrt(lengthSquared());
    }

    public Vector normalize(){
       this.head=(scale(1/length()).head);
       return this;
    }

    public Vector normalized(){
        Vector v=new Vector(this.head);
       return new Vector(v.normalize().head);
    }

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
}
