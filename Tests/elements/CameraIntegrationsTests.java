
package elements;

import geometries.Polygon;
import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.List;


public class CameraIntegrationsTests {
    @Test
    void integrationsCameraTests() {
        Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)).setVpDistance(1);
        Sphere sphere=new Sphere(1,new Point3D(0,0,-3));
        //Ray result=camera.constructRayThroughPixel(3,3,)

    }

    private boolean testfunc(Camera camera,List<Ray> rays){
        int index=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(!camera.constructRayThroughPixel(3,3,i,j).equals(rays.get(index++))){
                    return false;
                }
            }
        }
        return true;
    }
}
