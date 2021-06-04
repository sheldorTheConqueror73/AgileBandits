/* itamar ohana, id:314773169, mail: itamaroh7@gmail.com
   jonathan tamir, id:318168515, mail:yonatan.y.tamit@gmail.com
   credit to Dan Zilberstein, stack overflow, google
 */

import primitives.*;
import static java.lang.System.out;
import static primitives.Util.*;

public final class Main {

    /**
     * Main program to tests initial functionality of the 1st stage
     *
     * @param args irrelevant here
     */
    public static void main(String[] args) {

//        //coordinate test
//
//        Coordinate c1= new Coordinate(1.0);
//        Coordinate c2= new Coordinate(1);
//        Coordinate c3= new Coordinate(-1);
//        Coordinate c4= new Coordinate(0);
//        if(!c1.equals(c1))
//            out.println("Error: Coordinate object not equal to iteself");
//        if(!c1.equals(c2))
//            out.println("Error: Coordinate object not equal to object with same value");
//        if(c1.equals(c3))
//            out.println("Error: Coordinate object  equal to object with different value");
//        if(c1.equals(c4))
//            out.println("Error: Coordinate object not equal to object with same value (second case: c4 is zero)");
//
//        //point3D test
//        Point3D p11=new Point3D(1,2,3);
//        Point3D p13=new Point3D(-1,-2,-3);
//        Point3D p14=new Point3D(-1,-2,-3);
//
//      if(!p11.equals(p11))
//          out.println("Error:Point3D object not equal to iteself");
//       if(! p13.equals(p14))
//           out.println("Error:Point3D object not equal to object with same value");
//       if( p13.equals(p11))
//           out.println("Error:Point3D object  equal to object with different value");
//
//
//
//        try { // test zero vector
//            new Vector(0, 0, 0);
//            out.println("ERROR:vector zero vector does not throw an exception");
//        } catch (Exception e) {}
//
//        Vector v1 = new Vector(1, 2, 3);
//        Vector v2 = new Vector(-2, -4, -6);
//        Vector v3 = new Vector(0, 3, -2);
//        Vector v4 = new Vector(4, 8, 12);
//        Vector v5 = new Vector(1, 4, 3);
//        Vector v6 = new Vector(1, -4, -3);
//
//
//        // test length..
//        if (!isZero(v1.lengthSquared() - 14))
//            out.println("ERROR: lengthSquared() wrong value");
//        if (!isZero(new Vector(0, 3, 4).length() - 5))
//            out.println("ERROR: length() wrong value");
//
//        // test Dot-Product
//        if (!isZero(v1.dotProduct(v3)))
//            out.println("ERROR: dotProduct() for orthogonal vectors is not zero");
//        if (!isZero(v1.dotProduct(v2) + 28))
//            out.println("ERROR: dotProduct() wrong value");
//        if(!isZero(v1.dotProduct(v4)-56))
//            out.println("ERROR: dotProduct() for same direction vector wrong value");
//        if(!isZero(v1.dotProduct(v5)-18))
//            out.println("ERROR: dotProduct() for sharp angle vector wrong value");
//        if(!isZero(v1.dotProduct(v6)+16))
//            out.println("ERROR: dotProduct() for blunt angle vector wrong value");
//
//        // test Cross-Product
//        try { // test zero vector
//            v1.crossProduct(v2);
//            out.println("ERROR: crossProduct() for parallel vectors does not throw an exception");
//        } catch (Exception e) {}
//        Vector vr = v1.crossProduct(v3);
//        if (!isZero(vr.length() - v1.length() * v3.length()))
//            out.println("ERROR: crossProduct() wrong result length");
//        if (!isZero(vr.dotProduct(v1)) || !isZero(vr.dotProduct(v3)))
//            out.println("ERROR: crossProduct() result is not orthogonal to its operands");
//
//        try {
//            vr= v1.crossProduct(v4);
//            out.println("ERROR: crossProduct() for same direction vector wrong value");
//        }
//        catch (Exception e){ }
//          vr= v1.crossProduct(v5);
//        if(!vr.equals(new Vector (-6,0,2)))
//            out.println("ERROR: crossProduct() for sharp angle vector wrong value");
//        vr=v1.crossProduct(v6);
//        if(!vr.equals(new Vector(6,6,-6)))
//            out.println("ERROR: crossProduct() for blunt angle vector wrong value");
//
//
//        // test vector normalization vs vector length and cross-product
//        Vector v = new Vector(1, 2, 3);
//        Vector vCopy = new Vector(v.getHead());
//        Vector vCopyNormalize = vCopy.normalize();
//        if (vCopy != vCopyNormalize)
//            out.println("ERROR: normalize() function creates a new vector");
//        if (!isZero(vCopyNormalize.length() - 1))
//            out.println("ERROR: normalize() result is not a unit vector");
//        Vector u = v.normalized();
//        if (u == v)
//            out.println("ERROR: normalizated() function does not create a new vector");
//
//        // Test operations with points and vectors
//        Point3D p1 = new Point3D(1, 2, 3);
//        if (!Point3D.ZERO.equals(p1.add(new Vector(-1, -2, -3))))
//            out.println("ERROR: Point + Vector does not work correctly");
//        if (!new Vector(1, 1, 1).equals(new Point3D(2, 3, 4).subtract(p1)))
//            out.println("ERROR: Point - Point does not work correctly");
//
//        out.println("If there were no any other outputs - all tests succeeded!");
    }
}
