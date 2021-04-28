package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
import utility.tools;
public class SpotLight extends PointLight{
    private Vector direction;


    public SpotLight(Color intensity, Point3D pos, double kC, double kL, double kQ, Vector direction) {
        super(intensity, pos, kC, kL, kQ);
        this.direction = direction;
    }
    @Override
    public Color getIntensity(Point3D p) {
        var l=getL(p);
        double sf=tools.max(0.0,direction.dotProduct(l));
        return super.getIntensity().scale(sf);
    }
}
