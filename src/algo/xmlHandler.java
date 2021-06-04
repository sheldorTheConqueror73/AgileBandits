package algo;

import geometries.Geometries;
import geometries.Sphere;
import geometries.Triangle;
import primitives.*;
import elements.*;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import scene.Scene;

import javax.swing.*;

public class xmlHandler extends DefaultHandler {

    Geometries geometries = new Geometries();
    Color background=null, ambient=null;
    Scene scene = null;
    String name;

    /*  @Override
      public void startDocument() {
          scene=new Scene();
      }*/
    @Override
    public void startElement(String uri,
                             String localName,
                             String qName,
                             Attributes attributes) {

        Point3D p0, p1, p2;
        if (qName.equals("scene")) {
            String[] color = attributes.getValue("background-color").split(" ");
            try {
                background = new Color(Integer.parseInt(color[0]), Integer.parseInt(color[1]), Integer.parseInt(color[2]));
            } catch (NumberFormatException ecx) {
                JOptionPane.showMessageDialog(null, "no bg color");
                return;
            }
        }
        if (qName.equals("ambient-light")) {
            String[] color = attributes.getValue("color").split(" ");
            try {
                ambient = new Color(Integer.parseInt(color[0]), Integer.parseInt(color[1]), Integer.parseInt(color[2]));
            } catch (NumberFormatException ecx) {
                JOptionPane.showMessageDialog(null, "no ambient color");
                return;
            }

        }
        if (qName.equals("triangle")) {
            p0 = ToPoint(attributes.getValue("p0").split(" "), "p0");
            p1 = ToPoint(attributes.getValue("p1").split(" "), "p1");
            p2 = ToPoint(attributes.getValue("p2").split(" "), "p2");
            geometries.add(new Triangle(p0, p1, p2));
        }
        if (qName.equals("sphere")) {

            p0 = ToPoint(attributes.getValue("center").split(" "), "p0");
            int radius = Integer.parseInt(attributes.getValue("radius"));
            geometries.add(new Sphere(radius, p0));
        }
    }
    @Override
    public void endDocument() {
        scene= new Scene(name).setAmbientLight(new AmbientLight(ambient,1)).setBackground(background).setGeometries(geometries);
    }



    private Point3D ToPoint(String[] points, String ErrMsg) {
        Point3D p0;
        try {
            p0 = new Point3D(Integer.parseInt(points[0]), Integer.parseInt(points[1]), Integer.parseInt(points[2]));
            return p0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error Converting " + ErrMsg + "to point3D");
            return null;
        }
    }
    public Scene getScene() {
        return scene;
    }

    public xmlHandler setName(String name) {
        this.name = name;
        return this;
    }
}
