package algo;
import primitives.*;

import java.util.LinkedList;
import java.util.List;

import java.util.concurrent.ThreadLocalRandom;


public class SuperSampling {
    final static double pi2=2*Math.PI;

    public static List<Ray> mkRays(Ray ray,Point3D pl,int cnRays,double radius){
        Point3D pOrigin=ray.getP0();
        List<Point3D> points=new LinkedList<>();
        points.add(pOrigin);
        Point3D pDest;
        double x=pl.getX(),y=pl.getY(),z=pl.getZ();
        for(int i=0;i<cnRays;i++) {
            do {
                double rRadius=ThreadLocalRandom.current().nextDouble(0,radius);
                double rAngle=ThreadLocalRandom.current().nextDouble(0,pi2);
                double xp=rRadius*Math.cos(rAngle)+x;
                double xy=rRadius*Math.sin(rAngle)+ y;
                pDest=new Point3D(xp,xy,z);
            } while (points.contains(pDest));
            points.add(pDest);
        }
        points.remove(pOrigin);
        List<Ray> rays=new LinkedList<>();
        for (var p:points) {
            rays.add(new Ray(pOrigin,p.subtract(pOrigin)));
        }
        return rays;
    }

}

