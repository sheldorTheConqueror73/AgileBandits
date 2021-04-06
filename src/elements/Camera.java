package elements;
import primitives.*;

import static primitives.Util.*;
public class Camera {
    final Point3D p0;
    final Vector vUp,vTo,vRight;
    double width,height,distance;

    public Camera(Point3D _p0, Vector _vTo, Vector _vUp) {
        p0 = _p0;
        vUp = _vUp.normalized();
        vTo = _vTo.normalized();
        if(!isZero(_vTo.dotProduct(_vUp))){
            throw new IllegalArgumentException("the vectors must be a vertical");
        }
        vRight=vTo.crossProduct(vUp);
    }

    public Point3D getP0() {
        return p0;
    }

    public Vector getvUp() {
        return vUp;
    }

    public Vector getvTo() {
        return vTo;
    }

    public Vector getvRight() {
        return vRight;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getDistance() {
        return distance;
    }

    public Camera setVpSize(double _width, double _height){
        this.height=_height;
        this.width=_width;
        return this;
    }

    public Camera setVpDistance(double _distance){
        this.distance=_distance;
        return this;
    }

    public Ray constructRayThroughPixel(int nX, int nY, int j, int i){
        Point3D pc=p0.add(vTo.scale(distance));

        double rY=height/nY;
        double rX=width/nX;

        double yI=-(i-(nY-1)/2d)*rY;
        double xJ=(j-(nX-1)/2d)*rX;

        Point3D pIJ=pc;
        if(!isZero(xJ)){
            pIJ=pIJ.add(vRight.scale(xJ));
        }

        if(!isZero(yI)){
            pIJ=pIJ.add(vUp.scale(yI));
        }

        Vector vIJ=pIJ.subtract(p0);

        return new Ray(p0,vIJ);

    }

}
