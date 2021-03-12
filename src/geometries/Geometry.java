package geometries;

import primitives.*;

/**
 * interface Geometry is the class representing a Geometry for Cartesian
 * coordinate system.
 */
public interface Geometry {

    /**
     * returns normal
     * @param point
     * @return
     */
    public Vector getNormal(Point3D point);
}
