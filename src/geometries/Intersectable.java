package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public interface Intersectable {
    default List<Point3D> findIntersections(Ray ray)
    {
        List<GeoPoint> geoList= findGeoIntersections(ray);
        return geoList== null?
                null:
                geoList.stream().map(gp-> gp.point).collect(Collectors.toList());

    }

    public static class GeoPoint {
        public Geometry geometry;
        public Point3D point;


        public GeoPoint(Geometry geometry,Point3D point) {
            this.point = point;
            this.geometry=geometry;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return this.geometry.equals(geoPoint.geometry) && this.point.equals(geoPoint.point);
        }
    }
    public List<GeoPoint> findGeoIntersections(Ray ray);


}
