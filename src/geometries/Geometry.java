package geometries;

import primitives.*;

/**
 * interface Geometry is the class representing a Geometry for Cartesian
 * coordinate system.
 */
public interface Geometry {

    public Vector getNormal(Point3D point);
}
