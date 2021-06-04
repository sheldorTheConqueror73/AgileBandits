package geometries;

import primitives.*;


import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Pyramid extends Geometry{
    private Rectangle base;
    private Point3D apex;
    private Triangle[] sides;

    public Pyramid(Rectangle bs,Point3D tp){
        this.base=bs;
        this.apex=tp;
        sides=new Triangle[4];
        for(int i=0;i<4;i++){
            sides[i]= new Triangle(new Point3D(apex.getX(), apex.getY(), apex.getZ())
                    ,new Point3D(base.vertices.get(i).getX(),base.vertices.get(i).getY(),base.vertices.get(i).getZ())
                    ,new Point3D(base.vertices.get((i+1)%4).getX(),base.vertices.get((i+1)%4).getY(),base.vertices.get((i+1)%4).getZ()));
        }
    }
    

    @Deprecated
    public List<Point3D> findIntersections(Ray ray) {
           return this.findIntersections(ray);
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        List<GeoPoint> intersections= new LinkedList<>();
        for (var item : sides) {
            var points=item.findGeoIntersections(ray);
            if(points!=null) {
                intersections.addAll(points);
            }
        }
        var basePoints=base.findGeoIntersections(ray);
        if(basePoints!=null) {
            intersections.addAll(basePoints);
        }
        if(intersections.size()==0)
            return null;
        return intersections;
    }

    @Override
    public Pyramid setEmission(Color emmission) {
        for (var tr:sides) {
            tr.setEmission(emmission);
        }
        base.setEmission(emmission);
        return this;
    }

    public Pyramid setEmission2(Color emmission,int index) {
        if(index>=0&&index<5){
            if(index!=4){
                sides[index].setEmission(emmission);
            }else {
                base.setEmission(emmission);
            }
        }
        return this;
    }

    @Override
    public Pyramid setMaterial(Material material) {
        for (var tr:sides) {
            tr.setMaterial(material);
        }
        base.setMaterial(material);
        return this;
    }

    public Pyramid setMaterial2(Material material,int index) {
        if(index>=0&&index<5){
            if(index!=4){
                sides[index].setMaterial(material);
            }else {
                base.setMaterial(material);
            }
        }
        return this;
    }

    @Override
    public Vector getNormal(Point3D point){

        for (var item:sides) {
            double totalArea= item.area();
            double area1= item.area(point,item.vertices.get(0),item.vertices.get(1));
            double area2= item.area(point,item.vertices.get(1),item.vertices.get(2));
            double area3= item.area(point,item.vertices.get(2),item.vertices.get(0));
            if(isZero(totalArea-(area1+area2+area3)))
                return item.getNormal(point);
        }
        double totalArea= base.area();
        double area1= Triangle.area(point,base.vertices.get(0),base.vertices.get(1));
        double area2= Triangle.area(point,base.vertices.get(1),base.vertices.get(2));
        double area3= Triangle.area(point,base.vertices.get(2),base.vertices.get(3));
        double area4= Triangle.area(point,base.vertices.get(3),base.vertices.get(0));
        if(isZero(totalArea-(area1+area2+area3+area4)))
            return base.getNormal(point);
        return null;

    }
}
