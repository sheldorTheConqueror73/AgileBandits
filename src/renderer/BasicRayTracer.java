package renderer;

import algo.SuperSampling;
import elements.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;

public class BasicRayTracer extends RayTracerBase {
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final double INITIAL_K = 1.0;
    private static  int COUNT_RAYS=100;
    private static  double RADIUS_SAMPLE=10.0;
    public static  boolean flagSoftShadows=false;
    private static String samplingAlgo="RANDOM";

    /**
     * ctor
     * @param _scene
     */
    public BasicRayTracer(Scene _scene) {
        super(_scene);
    }

    /**
     * set super sampling's algorithm kind
     * @param samplingAlgo string of the kind
     * @return
     */
    public BasicRayTracer setSamplingAlgo(String samplingAlgo) {
        if(samplingAlgo!="RANDOM" && samplingAlgo!="DISTRIBUTED")
            throw new IllegalArgumentException("you must select one of the two allowed algorithems");
        BasicRayTracer.samplingAlgo = samplingAlgo;
        return this;
    }

    /**
     * setter
     * @param countRays count of rays for super Sampling
     * @return
     */
    public  BasicRayTracer setCountRays(int countRays) {
        COUNT_RAYS = countRays;
        return this;
    }

    /**
     * setter
     * @param flagSoftShadows true if do the soft shadows algo
     * @return
     */
    public  BasicRayTracer setFlagSoftShadows(boolean flagSoftShadows) {
        BasicRayTracer.flagSoftShadows = flagSoftShadows;
        return this;
    }

    /**
     * setter
     * @param radiusSample radius of the super sampling
     * @return
     */
    public  BasicRayTracer setRadiusSample(double radiusSample) {
        RADIUS_SAMPLE = radiusSample;
        return this;
    }

    /**
     * traces ray and calcs color
     * @param ray ray to trace
     * @return
     */
    @Override
    public Color traceRay(Ray ray) {
        GeoPoint closestPoint = findClosestIntersection(ray);
        return closestPoint == null ? scene.background : calcColor(closestPoint, ray);
    }

    /**
     * return the color of pixel
     * @param c0 top left corner
     * @param c1 top right corner
     * @param c2 bottom left corner
     * @param c3 bottom right corner
     * @param camPos camera position
     * @param level recursion level
     * @return
     */
    @Override
    public  Color adaptiveTrace(Point3D c0,Point3D c1,Point3D c2,Point3D c3,Point3D camPos,int level ){
        return adaptiveCalc(c0,c1,c2,c3,camPos,level);
    }

    /**
     * retrun pixel's color
     * @param geoPoint geometry and point
     * @param ray ray
     * @return
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        return calcColor(geoPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.ambientLight.getIntensity());
    }


    /**
     * calcs color at goepoint
     * @param point point
     * @param ray ray
     * @return
     */
    Color calcColor(GeoPoint point, Ray ray,int level,double k) {
       Color color=point.geometry.getEmission();
       color= color.add(calcLocalEffects(point,ray,k));
       return 1==level?color:color.add(calcGlobalEffects(point,ray.getDir(),level,k));


    }

    /**
     * return the global effect color
     * @param gp geomtery and point
     * @param v vector of the ray
     * @param level recursion level
     * @param k initial k (kt,ktr...)
     * @return
     */
    private Color calcGlobalEffects(GeoPoint gp, Vector v, int level, double k) {
        Color color = Color.BLACK; Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        double kkr = k * material.kR;
        if (kkr > MIN_CALC_COLOR_K)
            color = calcGlobalEffect(constructReflectedRay(n,gp.point,v), level, material.kR, kkr);
        double kkt = k * material.kT;
        if (kkt > MIN_CALC_COLOR_K)
            color = color.add(
                    calcGlobalEffect(constructRefractedRay(n,gp.point, v), level, material.kT, kkt));
        return color;
    }

    /**
     *
     * @param ray ray
     * @param level recrusion level
     * @param kx the lest level
     * @param kkx this level
     * @return
     */
    private Color calcGlobalEffect(Ray ray, int level, double kx, double kkx) {
        GeoPoint gp = findClosestIntersection(ray);
        if(gp==null){
            return scene.background.scale(kx);
        }
        Color temp=calcColor(gp, ray, level-1, kkx);
        return temp.scale(kx);
    }

    /**
     * return ray of reflected
     * @param n normal
     * @param point point on the geometry
     * @param v direction of the ray
     * @return
     */
    private Ray constructReflectedRay(Vector n, Point3D point, Vector v){
        double temp = v.dotProduct(n)*2;
        Vector r = v.subtract(n.scale(temp));
        return new Ray(point,r,n);
    }

    /**
     * return ray of  reflaction
     * @param n normal
     * @param point point on the geometry
     * @param v the ray's direction
     * @return
     */
    private Ray constructRefractedRay(Vector n,Point3D point,Vector v){
        return new Ray(point,v,n);
    }

    private GeoPoint findClosestIntersection(Ray ray){
        var points=scene.geometries.findGeoIntersections(ray);
        return ray.getClosestGeoPoint(points);
    }

    /**
     * clacs local effect
     * @param intersection intersection point
     * @param ray ray
     * @return
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray,double k) {
        Vector v = ray.getDir();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return Color.BLACK;
        int nShininess = intersection.geometry.getMaterial().getnShininess();
        double kd = intersection.geometry.getMaterial().getkD(),
                ks = intersection.geometry.getMaterial().getkS();
        Color color = Color.BLACK;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) {// sign(nl) == sing(nv)
                double ktr=transparency(lightSource,l,n, intersection);
                if (ktr * k > MIN_CALC_COLOR_K) {
                    Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);
                    color = color.add(lightIntensity.scale(calcDiffusive(kd, l, n) + calcSpecular(ks, l, n, v, nShininess)));
                }
            }
        }
        return color;
    }

    /**
     * calcs diffusive effect
     * @param kd kd coefficient
     * @param l l vector
     * @param n n vector
     * @return
     */
   private double calcDiffusive(double kd,Vector l ,Vector n) {
       return kd*(Math.abs(l.dotProduct(n)));//add argument l*n
   }

    /**
     * calcs specular effecr
     * @param ks ks coefficient
     * @param l  l vector
     * @param n n vector
     * @param v vector
     * @param nShininess shininess coefficient
     * @return
     */
    private double calcSpecular(double ks,Vector l ,Vector n,Vector v,int nShininess ) {
        Vector temp= n.scale(2*l.dotProduct(n));
        Vector r = l.subtract(temp);
        Vector temp2= v.scale(-1);
        double num= Math.max(0,temp2.dotProduct(r));
        return ks*(Math.pow(num,nShininess));
    }

    /**
     * return precent of the shadow at specific point
     * @param ls light source
     * @param l  vector from point to light
     * @param n normal
     * @param geopoint geometry and point
     * @return
     */
    private double transparency(LightSource ls, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        double sumKtr = 0.0, baseKtr;
        Ray lightRay = new Ray(geopoint.point, lightDirection, n);
        baseKtr = calKtr( geopoint, lightRay,ls);

        if ( flagSoftShadows) {
            List<Ray> beam;
            if(samplingAlgo=="DISTRIBUTED")
                beam = SuperSampling.distributedSample(lightRay,
                    geopoint.point.add(lightDirection.scale(ls.getDistance(geopoint.point))), COUNT_RAYS,RADIUS_SAMPLE);
            else
                beam = SuperSampling.randomSample(lightRay,
                        geopoint.point.add(lightDirection.scale(ls.getDistance(geopoint.point))), COUNT_RAYS,RADIUS_SAMPLE);
            for (int i = 1; i < beam.size(); i++) {
                sumKtr += calKtr(geopoint, beam.get(i),ls);
            }
            baseKtr = (sumKtr + baseKtr) / beam.size();
        }

        return baseKtr;
    }


    /**
     * return the ktr
     * @param geoPoint point and geometry
     * @param ray vector from light to point
     * @param ls light source
     * @return
     */
    private double calKtr(GeoPoint geoPoint,Ray ray,LightSource ls){
        List<GeoPoint> intersections=scene.geometries.findGeoIntersections(ray);
        double lightDistance=ls.getDistance(geoPoint.point);
        double ktr=1.0;
        if(intersections==null){
            return ktr;
        }
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(geoPoint.point)-lightDistance) <= 0) {
                ktr *= gp.geometry.getMaterial().getKt();
                if (ktr < MIN_CALC_COLOR_K){
                    return 0.0;
                }
            }
        }
        return ktr;
    }

    /**
     * return pixel's color
     * @param p0 top left
     * @param p1 top right
     * @param p2 bottom left
     * @param p3 bottom right
     * @param camPos camera position
     * @param level recursion level
     * @return
     */
    public Color adaptiveCalc(Point3D p0,Point3D p1,Point3D p2,Point3D p3,Point3D camPos,int level){
        List<Point3D> edges= List.of(p0,p1,p2,p3);
        List<Color> colors= new ArrayList<Color>();
        for (int i=0;i<4;i++){
            Ray ray= new Ray(camPos,edges.get(i).subtract(camPos).normalized());
            GeoPoint point= findClosestIntersection(ray);
            if(point!=null){
                colors.add(calcColor(point,ray));//calc the corner's color.
            }
            else{
                colors.add(scene.background);
            }
        }
            if((colors.get(0).equals(colors.get(1))&&colors.get(0).equals(colors.get(2))&&colors.get(0).equals(colors.get(3))) || level==0){
                return colors.get(0);//if all the corners have the same color.
            }
            //calc the middle between two corners:
            Point3D up=edges.get(0).middleOf(edges.get(1));
            Point3D down=edges.get(2).middleOf(edges.get(3));
            Point3D r=edges.get(1).middleOf(edges.get(3));
            Point3D l=edges.get(0).middleOf(edges.get(2));
            Point3D center=r.middleOf(l);

            //recursive color calc
            return adaptiveCalc(edges.get(0),up,l,center,camPos,level-1).scale(0.25d)
                    .add(adaptiveCalc(up,edges.get(1),center,r,camPos,level-1).scale(0.25d))
                    .add(adaptiveCalc(l,center,edges.get(2),down,camPos,level-1).scale(0.25d))
                    .add(adaptiveCalc(center,r,down,edges.get(3),camPos,level-1).scale(0.25d));

    }



}

