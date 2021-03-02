package primitives;


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

}
