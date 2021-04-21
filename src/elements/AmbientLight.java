package elements;
import  primitives.Color;

public class AmbientLight {
    private final Color intensity;

    public Color getIntensity() {
        return intensity;
    }

    public AmbientLight(Color iA,double kA) {
        intensity = iA.scale(kA);
    }
}
