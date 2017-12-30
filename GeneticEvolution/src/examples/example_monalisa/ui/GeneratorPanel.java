package examples.example_monalisa.ui;

import genetic.element.Gene;
import genetic.element.Population;
import examples.example_monalisa.LisaGene;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;

public class GeneratorPanel extends JPanel {

    private Population population;

    @Override
    public void paint(Graphics g1) {

        Graphics2D g = (Graphics2D) g1;

        super.paint(g);

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

        if (population != null && population.getFittest() != null) {
            drawIntoImage((Graphics2D) g);
        }

    }

    private void drawIntoImage(Graphics2D g) {

        AffineTransform scaleMatrix = new AffineTransform();
        scaleMatrix.scale(2, 2);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (Gene gene : population.getFittest()) {

            LisaGene l = (LisaGene) gene;
            float alpha = (float) l.getColor().getAlpha() / 256;

            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g.setColor(l.getColor());

            g.setTransform(scaleMatrix);
            g.fill(l.getPolygon());

        }
    }

    public void update(Population p) {
        this.population = p;
        repaint();

    }

}
