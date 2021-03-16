package geometries;

import primitives.*;

/**
 * interface Geometry is the class representing a Geometry for Cartesian
 * coordinate system.
 */
public interface Geometry  extends  Intersectable{

    /**
     * returns normal
     * @param point
     * @return
     */
    public Vector getNormal(Point3D point);
}
