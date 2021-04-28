package geometries;

import primitives.*;

/**
 * interface Geometry is the class representing a Geometry for Cartesian
 * coordinate system.
 */
public abstract class Geometry  implements   Intersectable{

    protected Color emmission=Color.BLACK;
    private  Material material=new Material();




    /**
     * returns normal
     * @param point
     * @return
     */
    public abstract Vector getNormal(Point3D point);

    public Color getEmission() {
        return emmission;
    }

    public Geometry setEmission(Color emmission) {
        this.emmission = emmission;
        return this;
    }

    public Material getMaterial() {
        return material;
    }

    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }
}
