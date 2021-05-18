package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
import utility.tools;
public class SpotLight extends PointLight{
    private Vector direction;


    public SpotLight(Color intensity, Point3D pos, Vector direction) {
        super(intensity, pos);
        this.direction = direction.normalized();
    }
    @Override
    public Color getIntensity(Point3D p) {
        var l=getL(p);
        double sf=tools.max(0.0,direction.dotProduct(l));
        return super.getIntensity().scale(sf);
    }
}
