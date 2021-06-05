package algo;
import primitives.*;

import java.util.LinkedList;
import java.util.List;

import java.util.concurrent.ThreadLocalRandom;


public class SuperSampling {
    final static double pi2=2*Math.PI;
    final static  double EPSILON=0.0000000001;

    /*
     * @param ray main ray
     * @param pl light source point
     * @param cnRays amount of rays (must be integer^2)
     * @param offset size of sqaure side
     * @return list of rays to sample
     */
    public static List<Ray> randomSample(Ray ray,Point3D pl,int cnRays,double radius){
        Point3D pOrigin=ray.getP0(); //calc ray origin point
        List<Point3D> points=new LinkedList<>();//list of sampling points
        points.add(pOrigin); // add the original point (in order to make sure we dont sample it twice)
        Point3D pDest;       //dst point to add to point list
        double x=pl.getX(),y=pl.getY(),z=pl.getZ(); //storing light source x,y,x for later use
        for(int i=0;i<cnRays;i++) {
            do {
                double rRadius=ThreadLocalRandom.current().nextDouble(0,radius); //calc random radius from main ray
                double rAngle=ThreadLocalRandom.current().nextDouble(0,pi2); //calc random angle from main ray
                double xp=rRadius*Math.cos(rAngle)+x;   //converting distance + angle to new point
                double xy=rRadius*Math.sin(rAngle)+ y;
                pDest=new Point3D(xp,xy,z);
            } while (points.contains(pDest));       //checking that the point doesnt exist already in the list
            points.add(pDest);                  //adding the new point
        }
        points.remove(pOrigin);                 //deleting main point (to avoid double sampling)
        List<Ray> rays=new LinkedList<>();
        for (var p:points) {
            rays.add(new Ray(pOrigin,p.subtract(pOrigin))); //turning each point to a ray
        }
        return rays;
    }

    /*
     * @param ray main ray
     * @param pl light source point
     * @param cnRays amount of rays (must be integer^2)
     * @param offset size of sqaure side
     * @return list of rays to sample
     */
    public static List<Ray> distributedSample(Ray ray,Point3D pl,int cnRays,double offset){
        double temp =Math.sqrt(cnRays);
        if(temp!=(int)temp)
            throw new IllegalArgumentException("cntRays must be a square of an int");
        final double interval=offset/2*temp;
        Point3D pOrigin=ray.getP0();
        List<Point3D> points=new LinkedList<>();
        points.add(pOrigin);
        Point3D pDest;
        double x=pl.getX(),y=pl.getY(),z=pl.getZ();
        Point3D curr;
        int r=(int)(temp-(temp/2));
        int l=(int)(temp-r);
        int j=1;
        for(int i=0;i<r;i++) {
            if(i!=0)
                j=0;
            for(;j<temp;j++){
                curr=new Point3D(x+(interval*i),y+(interval*j),z);
                double randX=ThreadLocalRandom.current().nextDouble(0,interval-EPSILON);
                double randY=ThreadLocalRandom.current().nextDouble(0,interval-EPSILON);
                points.add(new Point3D(x+randX,y+randY,z));
            }
        }
        for(int i=0;i<l;i++) {
            for(j=0;j<temp;j++){
                curr=new Point3D(x-(interval*i),y-(interval*j),z);
                double randX=ThreadLocalRandom.current().nextDouble(0,interval-EPSILON);
                double randY=ThreadLocalRandom.current().nextDouble(0,interval-EPSILON);
                points.add(new Point3D(x-randX,y-randY,z));
            }
        }
        points.remove(pOrigin);
        List<Ray> rays=new LinkedList<>();
        for (var p:points) {
            rays.add(new Ray(pOrigin,p.subtract(pOrigin)));
        }
        return rays;
    }


}

