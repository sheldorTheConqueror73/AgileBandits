package algo;
import primitives.*;

import java.util.LinkedList;
import java.util.List;

import java.util.concurrent.ThreadLocalRandom;

import static primitives.Util.isZero;


public class SuperSampling {
    final static double pi2=2*Math.PI;
    final static  double EPSILON=0.0000000001;

    /*
     * @param ray main ray
     * @param center point of beam
     * @param cnRays amount of rays (must be integer^2)
     * @param offset size of sqaure side
     * @return list of rays to sample
     */
    public static List<Ray> randomSample(Ray ray, Point3D center, int cnRays, double radius){
        Point3D pOrigin=ray.getP0(); //calc ray origin point
        List<Point3D> points=new LinkedList<>();//list of sampling points
        points.add(pOrigin); // add the original point (in order to make sure we dont sample it twice)
        Point3D pDest;       //dst point to add to point list
        double x=center.getX(),y=center.getY(),z=center.getZ(); //storing light source x,y,x for later use
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
     * @param center point of beam
     * @param cnRays amount of rays (must be integer^2)
     * @param offset size of sqaure side
     * @return list of rays to sample
     */
    public static List<Ray> distributedSample(Ray ray, Point3D center, int cnRays, double offset){
        double temp =Math.sqrt(cnRays);
        if(temp!=(int)temp)
            throw new IllegalArgumentException("cntRays must be a square of an int");
        final double interval=offset/2*temp;//calc square interval
        Point3D pOrigin=ray.getP0();//get src
        List<Point3D> points=new LinkedList<>();
        points.add(pOrigin);//add src point to list
        Point3D pDest;
        double x=center.getX(),y=center.getY(),z=center.getZ();//calc x,y,z of dst point
        Point3D curr;
        int r=(int)(temp-(temp/2));//calc right side of pixel
        int l=(int)(temp-r);//left side of pixel
        int j=1;//to avoid sampling the main ray twice
        for(int i=0;i<r;i++) {//iterate over right side of pixel
            if(i!=0)
                j=0;//to avoid sampling the main ray twice
            for(;j<temp;j++){
                curr=new Point3D(x+(interval*i),y+(interval*j),z);
                double randX=ThreadLocalRandom.current().nextDouble(0,interval-EPSILON);//calc x offest
                double randY=ThreadLocalRandom.current().nextDouble(0,interval-EPSILON);//calc y offset
                points.add(new Point3D(x+randX,y+randY,z));//add new point
            }
        }
        for(int i=0;i<l;i++) {//iterate over left side of pixel
            for(j=0;j<temp;j++){
                curr=new Point3D(x-(interval*i),y-(interval*j),z);
                double randX=ThreadLocalRandom.current().nextDouble(0,interval-EPSILON);
                double randY=ThreadLocalRandom.current().nextDouble(0,interval-EPSILON);
                points.add(new Point3D(x-randX,y-randY,z));
            }
        }
        points.remove(pOrigin);//remove origin to avoid sampling main ray twice
        List<Ray> rays=new LinkedList<>();
        for (var p:points) {//convert points to rays
            rays.add(new Ray(pOrigin,p.subtract(pOrigin)));
        }
        return rays;
    }



}

