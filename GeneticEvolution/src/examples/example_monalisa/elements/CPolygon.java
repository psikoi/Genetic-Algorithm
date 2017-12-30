package examples.example_monalisa.elements;

import java.awt.Polygon;

public class CPolygon extends Polygon {
    
    public CPolygon copy(){
        CPolygon  c = new CPolygon();
        for (int i = 0; i < c.xpoints.length; i++) {
           c.addPoint(xpoints[i], ypoints[i]);
        }
        return c;
    }

}
