package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class PointLight extends Light implements LightSource{
    private  Point3D pos;
    private double kC,kL,kQ;

    public PointLight(Color intensity, Point3D pos, double kC, double kL, double kQ) {
        super(intensity);
        this.pos = pos;
        this.kC = kC;
        this.kL = kL;
        this.kQ = kQ;
    }

    @Override
    public Color getIntensity(Point3D p) {
        double d=pos.distance(p);
        double sf=1.0/(kC+(d*kL)+(d*d*kQ));
        return getIntensity().scale(sf);
    }

    @Override
    public Vector getL(Point3D p) {
       return  new Vector(pos.subtract(p).getHead()).normalized();
    }
}
