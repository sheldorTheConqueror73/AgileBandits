package elements;
import primitives.*;

import static primitives.Util.*;
public class Camera {
    final Point3D p0;
    final Vector vUp,vTo,vRight;
    double width,height,distance;

    /**
     * ctor
     * @param _p0   point p0
     * @param _vTo  vector to
     * @param _vUp  vector up
     */
    public Camera(Point3D _p0, Vector _vTo, Vector _vUp) {
        p0 = _p0;
        vUp = _vUp.normalized();
        vTo = _vTo.normalized();
        if(!isZero(_vTo.dotProduct(_vUp))){
            throw new IllegalArgumentException("the vectors must be a vertical");
        }
        vRight=vTo.crossProduct(vUp);
    }

    /**
     *
     * @return po
     */
    public Point3D getP0() {
        return p0;
    }

    /**
     *
     * @return vetor up
     */
    public Vector getvUp() {
        return vUp;
    }

    /**
     *
     * @return vector to
     */
    public Vector getvTo() {
        return vTo;
    }

    /**
     *
     * @return vector right
     */
    public Vector getvRight() {
        return vRight;
    }

    /**
     *
     * @return width
     */
    public double getWidth() {
        return width;
    }

    /**
     *
     * @return height
     */
    public double getHeight() {
        return height;
    }
    /**
     *
     * @return distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     *
     * @param _width of the view plane
     * @param _height of the view plane
     * @return this camera with new view plane width and height
     */
    public Camera setVpSize(double _width, double _height){
        this.height=_height;
        this.width=_width;
        return this;
    }

    /**
     *
     * @param _distance of the view plane
     * @return  this camera with new view plane distance
     */
    public Camera setVpDistance(double _distance){
        this.distance=_distance;
        return this;
    }

    /**
     *
     * @param nX amount of  columns in view plane
     * @param nY amount of  rows in view plane
     * @param j the column index of the pixel
     * @param i the row index of the pixel
     * @return ray of specific pixel
     */
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
