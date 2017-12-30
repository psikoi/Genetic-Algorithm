package examples.example_monalisa;

import genetic.element.Gene;
import examples.example_monalisa.elements.CPolygon;
import java.awt.Color;

public class LisaGene implements Gene {

    private CPolygon polygon;
    private Color color;

    public LisaGene(CPolygon polygon, Color color) {
        this.polygon = polygon;
        this.color = color;
    }

    public CPolygon getPolygon() {
        return polygon;
    }

    public void setCircle(CPolygon polygon) {
        this.polygon = polygon;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color + "";
    }

    @Override
    public Gene copy() {
        return new LisaGene(polygon.copy(), new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()));
    }

}
