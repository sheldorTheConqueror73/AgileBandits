package elements;
import  primitives.Color;

public class AmbientLight extends  Light{


    /*
    ctor
     */
    public AmbientLight(Color iA,double kA) {
        super(iA.scale(kA));
    }

}
