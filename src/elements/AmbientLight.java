package elements;
import  primitives.Color;

public class AmbientLight extends  Light{

    public AmbientLight() {
        super(Color.BLACK);
    }

    /*
        ctor
         */
    public AmbientLight(Color iA,double kA) {
        super(iA.scale(kA));
    }

}
