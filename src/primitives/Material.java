package primitives;

public class Material {
    int nShininess=0;
    double kD=0,kS=0;
    public double kT=0.0,kR=0.0;

    public double getKt() {
        return kT;
    }

    public double getKr() {
        return kR;
    }

    public Material setKd(double kD) {
        this.kD = kD;
        return this;
    }

    public Material setKt(double kT) {
        this.kT = kT;
        return this;
    }

    public Material setKr(double kR) {
        this.kR = kR;
        return this;
    }

    public Material setKs(double kS) {
        this.kS = kS;
        return this;
    }

    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    public int getnShininess() {
        return nShininess;
    }

    public double getkD() {
        return kD;
    }

    public double getkS() {
        return kS;
    }
}
