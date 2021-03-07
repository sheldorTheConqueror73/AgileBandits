package primitives;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VectorTest {
    Vector v1=new Vector(1,0,1);
    Vector v2=new Vector(0,1,0);
    Vector v3=new Vector(0,-1,0);
    Vector v4=new Vector(-1,0,1);
    @Test
    void testAdd(){
        assertTrue(v1.add(v2).equals(new Vector(1,1,1)));
        assertTrue(v1.add(v1).equals(new Vector(2,0,2)));
        assertTrue(v3.add(v4).equals(new Vector(-1,-1,1)));
    }
    @Test
    void testSub(){
        assertTrue(v1.subtract(v2).equals(new Vector(1,-1,1)));
        assertThrows(IllegalArgumentException.class,()->v1.subtract(v1).equals(new Vector(0,0,0)));
        assertTrue(v3.subtract(v4).equals(new Vector(1,-1,-1)));
    }
    @Test
    void testSacle(){
        assertThrows(IllegalArgumentException.class,()->v1.scale(0));
        assertTrue(v4.scale(2.5).equals(new Vector(-2.5,0,2.5)));
        assertTrue(v4.scale(-1).equals(new Vector(1,0,-1)));
    }




}