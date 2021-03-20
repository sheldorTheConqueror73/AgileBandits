package geometries;

import java.util.List;

import primitives.*;

import static primitives.Util.*;

/**
 * Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 *
 * @author Dan
 */
public class Polygon implements Geometry {
    /**
     * List of polygon's vertices
     */
    protected List<Point3D> vertices;
    /**
     * Associated plane in which the polygon lays
     */
    protected Plane plane;

    /**
     * Polygon constructor based on vertices list. The list must be ordered by edge
     * path. The polygon must be convex.
     *
     * @param vertices list of vertices according to their order by edge path
     * @throws IllegalArgumentException in any case of illegal combination of
     *                                  vertices:
     *                                  <ul>
     *                                  <li>Less than 3 vertices</li>
     *                                  <li>Consequent vertices are in the same
     *                                  point
     *                                  <li>The vertices are not in the same
     *                                  plane</li>
     *                                  <li>The order of vertices is not according
     *                                  to edge path</li>
     *                                  <li>Three consequent vertices lay in the
     *                                  same line (180&#176; angle between two
     *                                  consequent edges)
     *                                  <li>The polygon is concave (not convex)</li>
     *                                  </ul>
     */
    public Polygon(Point3D... vertices) {
        if (vertices.length < 3)
            throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
        this.vertices = List.of(vertices);
        // Generate the plane according to the first three vertices and associate the
        // polygon with this plane.
        // The plane holds the invariant normal (orthogonal unit) vector to the polygon
        plane = new Plane(vertices[0], vertices[1], vertices[2]);
        if (vertices.length == 3)
            return; // no need for more tests for a Triangle

        Vector n = plane.getNormal();

        // Subtracting any subsequent points will throw an IllegalArgumentException
        // because of Zero Vector if they are in the same point
        Vector edge1 = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
        Vector edge2 = vertices[0].subtract(vertices[vertices.length - 1]);

        // Cross Product of any subsequent edges will throw an IllegalArgumentException
        // because of Zero Vector if they connect three vertices that lay in the same
        // line.
        // Generate the direction of the polygon according to the angle between last and
        // first edge being less than 180 deg. It is hold by the sign of its dot product
        // with
        // the normal. If all the rest consequent edges will generate the same sign -
        // the
        // polygon is convex ("kamur" in Hebrew).
        boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
        for (int i = 1; i < vertices.length; ++i) {
            // Test that the point is in the same plane as calculated originally
            if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
                throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
            // Test the consequent edges have
            edge1 = edge2;
            edge2 = vertices[i].subtract(vertices[i - 1]);
            if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
                throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
        }
    }

    /**
     * returns normal
     *
     * @param point
     * @return
     */
    @Override
    public Vector getNormal(Point3D point) {
        return plane.getNormal();
    }

    @Override
    public String toString() {
        return "Polygon:" +
                "vertices=" + vertices +
                ", plane=" + plane;
    }

    /**
     * finds intersections with polygon and ray
     * @param ray ray to intersect
     * @return a list of intersection points or null if there are none
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        Vector[] arrVector = new Vector[vertices.size()];
        Vector[] arrNormal = new Vector[vertices.size()];
        Point3D p0 = ray.getP0();
        for (int i = 0; i < arrVector.length; i++) {
            arrVector[i] = new Vector(this.vertices.get(i).subtract(p0).getHead());
        }

        arrNormal[0]=arrVector[0].crossProduct(arrVector[1]);
        arrNormal[0].normalize();
        boolean sign=ray.getDir().dotProduct(arrNormal[0])>0;

        for (int i = 1; i < arrVector.length - 1; i++) {
            arrNormal[i]=arrVector[i].crossProduct(arrVector[i+1]);
            arrNormal[i].normalize();

            if (isZero(alignZero(ray.getDir().dotProduct(arrNormal[i])))) {
                return null;
            }

            boolean signTemp = ray.getDir().dotProduct(arrNormal[i]) > 0;
            if (sign != signTemp) {
                return null;
            }

        }
        arrNormal[arrNormal.length - 1] = arrVector[arrVector.length - 1].crossProduct(arrVector[0]);
        arrNormal[arrNormal.length - 1].normalize();
        if (isZero(alignZero(ray.getDir().dotProduct(arrNormal[arrNormal.length - 1])))) {
            return null;
        }
        boolean signTemp=ray.getDir().dotProduct(arrNormal[arrNormal.length - 1])> 0;
        if (sign!=signTemp) {
            return null;
        }
        return plane.findIntersections(ray);
    }
}
