package primitives;
import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

public class VectorTest {
    Vector v1=new Vector(1,0,1);
    Vector v2=new Vector(0,1,0);
    Vector v3=new Vector(0,-1,0);
    Vector v4=new Vector(-1,0,1);
    Vector v5 = new Vector(1, 2, 3);
    Vector v6 = new Vector(-2, -4, -6);
    Vector v7 = new Vector(0, 3, -2);
    Vector v8 = new Vector(4, 8, 12);
    Vector v9 = new Vector(1, 4, 3);
    Vector v10 = new Vector(1, -4, -3);


    // ============ Equivalence Partitions Tests ==============
    Vector v11 = new Vector(0, 3, -2);
    @Test
    void testAdd(){
        //test vector with same value
        assertTrue(v1.add(v2).equals(new Vector(1,1,1)));
        //test vector with itself
        assertTrue(v1.add(v1).equals(new Vector(2,0,2)));
        //test vector with negative value
        assertTrue(v3.add(v4).equals(new Vector(-1,-1,1)));
    }
    @Test
    void testSub(){
        //test vector with same value
        assertTrue(v1.subtract(v2).equals(new Vector(1,-1,1)));
        //test vector with itself
        assertThrows(IllegalArgumentException.class,()->v1.subtract(v1).equals(new Vector(0,0,0)));
        //test vector with negative value
        assertTrue(v3.subtract(v4).equals(new Vector(1,-1,-1)));
    }
    @Test
    void testSacle(){
        //test scale by 0
        assertThrows(IllegalArgumentException.class,()->v1.scale(0));
        //test scale by a double
        assertTrue(v4.scale(2.5).equals(new Vector(-2.5,0,2.5)));
        //test scale by  a negative
        assertTrue(v4.scale(-1).equals(new Vector(1,0,-1)));
    }



    @Test
    void dotProduct() {
        assertTrue(isZero(v5.dotProduct(v7)),"ERROR: dotProduct() for orthogonal vectors is not zero");
        assertTrue(isZero(v5.dotProduct(v6) + 28),"ERROR: dotProduct() wrong value");
        assertTrue(isZero(v5.dotProduct(v8)-56),"ERROR: dotProduct() for same direction vector wrong value");
        assertTrue(isZero(v5.dotProduct(v9)-18),"ERROR: dotProduct() for sharp angle vector wrong value");
        assertTrue(isZero(v5.dotProduct(v10)+16),"ERROR: dotProduct() for blunt angle vector wrong value");
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    public void testCrossProduct() {


        Vector vr = v5.crossProduct(v11);
        // Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertEquals(v5.length() * v11.length(), vr.length(), 0.00001,"crossProduct() wrong result length");

        // Test cross-product result orthogonality to its operands
        assertTrue( isZero(vr.dotProduct(v5)),"crossProduct() result is not orthogonal to 1st operand");
        assertTrue( isZero(vr.dotProduct(v11)),"crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // test zero vector from cross-productof co-lined vectors
        try {
            v5.crossProduct(v6);
            fail("crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {}
    }


    @Test
    void lengthSquared() {
        assertTrue(isZero(v5.lengthSquared() - 14),"ERROR: lengthSquared() wrong value");
    }

    @Test
    void length() {
        assertTrue(isZero(new Vector(0, 3, 4).length() - 5),"ERROR: length() wrong value");
    }

    @Test
    void normalize() {
        Vector vCopy = new Vector(v5.getHead());
        Vector vCopyNormalize = vCopy.normalize();
        assertFalse((vCopy != vCopyNormalize),"ERROR: normalize() function creates a new vector");
        assertTrue(isZero(vCopyNormalize.length() - 1),"ERROR: normalize() result is not a unit vector");
    }

    @Test
    void normalized() {
        Vector u = v5.normalized();
        assertFalse(u == v5,"ERROR: normalizated() function does not create a new vector");
    }
}