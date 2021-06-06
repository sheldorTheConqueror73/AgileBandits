package geometries;

import primitives.*;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.isZero;

public class Cube extends Geometry{
    private Rectangle faces[];

    public Cube(Point3D p1,Point3D p2){
        faces=new Rectangle[6];
        double dist=Math.abs(p1.distance(p2));
        faces[0]=new Rectangle(p1,new Point3D(p1.getX(),p1.getY(),p1.getZ()+dist),new Point3D(p2.getX(),p2.getY(),p2.getZ()+dist),p2);
        faces[1]=new Rectangle(new Point3D(p2.getX(),p2.getY()-dist,p2.getZ()),new Point3D(p2.getX(),p2.getY()-dist,p2.getZ()+dist),new Point3D(p1.getX(),p1.getY()-dist,p1.getZ()+dist),new Point3D(p1.getX(),p1.getY()-dist,p1.getZ()));
        faces[2]=new Rectangle(p2,new Point3D(p2.getX(),p2.getY(),p2.getZ()+dist),new Point3D(p2.getX(),p2.getY()-dist,p2.getZ()+dist),new Point3D(p2.getX(),p2.getY()-dist,p2.getZ()));
        faces[3]=new Rectangle(new Point3D(p1.getX(),p1.getY()-dist,p1.getZ()),new Point3D(p1.getX(),p1.getY()-dist,p1.getZ()+dist),new Point3D(p1.getX(),p1.getY(),p1.getZ()+dist),p1);
        faces[4]=new Rectangle(new Point3D(p1.getX(),p1.getY(),p1.getZ()+dist),new Point3D(p1.getX(),p1.getY()-dist,p1.getZ()+dist),new Point3D(p2.getX(),p2.getY()-dist,p2.getZ()+dist),new Point3D(p2.getX(),p2.getY(),p2.getZ()+dist));
        faces[5]=new Rectangle(p2,new Point3D(p2.getX(),p2.getY()-dist,p2.getZ()),new Point3D(p1.getX(),p1.getY()-dist,p1.getZ()),p1);

    }

    @Override
    public Vector getNormal(Point3D point) {
        for (var item:faces) {
            double totalArea= item.area();
            double area1= Triangle.area(point,item.vertices.get(0),item.vertices.get(1));
            double area2= Triangle.area(point,item.vertices.get(1),item.vertices.get(2));
            double area3= Triangle.area(point,item.vertices.get(2),item.vertices.get(3));
            double area4= Triangle.area(point,item.vertices.get(3),item.vertices.get(0));
            if(isZero(totalArea-(area1+area2+area3+area4)))
                return item.getNormal(point);
        }

        return null;

    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        List<GeoPoint> intersections= new LinkedList<>();
        for (var item : faces) {
            var points=item.findGeoIntersections(ray);
            if(points!=null) {
                intersections.addAll(points);
            }
        }
        if(intersections.size()==0)
            return null;
        return intersections;
    }

    @Override
    public Cube setEmission(Color emmission) {
        for (var tr:faces) {
            tr.setEmission(emmission);
        }
        return this;
    }


    @Override
    public Cube setMaterial(Material material) {
        for (var tr:faces) {
            tr.setMaterial(material);
        }

        return this;
    }

    @Deprecated
    public List<Point3D> findIntersections(Ray ray) {
        return this.findIntersections(ray);
    }
}
