package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class PointLight extends Light implements LightSource{
    private  Point3D pos;
    private double kC,kL,kQ;

    public PointLight(Color intensity, Point3D pos) {
        super(intensity);
        this.pos = pos;
        this.kC=1;
        this.kL=0;
        this.kQ=0;
    }

    public PointLight setkC(double kC) {
        this.kC = kC;
        return this;
    }

    public PointLight setkL(double kL) {
        this.kL = kL;
        return this;
    }

    public PointLight setkQ(double kQ) {
        this.kQ = kQ;
        return this;
    }

    @Override
    public Color getIntensity(Point3D p) {
        double d=pos.distance(p);
        double sf=1.0/(kC+(d*kL)+(d*d*kQ));
        return getIntensity().scale(sf);
    }


    @Override
    public Vector getL(Point3D p) {
       return  new Vector(p.subtract(pos).getHead()).normalized();
    }

    @Override
    public double getDistance(Point3D point) {
        return pos.distance(point);
    }
}
