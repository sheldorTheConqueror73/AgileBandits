package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Util;
import primitives.Vector;

public class SpotLight extends PointLight{
    private Vector direction;


    public SpotLight(Color intensity, Point3D pos, Vector direction) {
        super(intensity, pos);
        this.direction = direction.normalized();
    }
    @Override
    public Color getIntensity(Point3D p) {
     /*  double cos = Util.alignZero(direction.dotProduct(super.getL(p)));
       cos = cos >0? cos:0;
       return super.getIntensity(p).scale(cos);
*/


        var l=getL(p);
        double sf=Math.max(0.0,Util.alignZero(direction.dotProduct(l)));
        return super.getIntensity(p).scale(sf);
    }
}
