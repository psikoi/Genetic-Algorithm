package examples.example_monalisa;

import genetic.element.Chromosome;
import genetic.FitnessEvaluator;
import genetic.element.Gene;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class LisaFitnessEvaluator implements FitnessEvaluator {

    private Chromosome target;
    private BufferedImage current;
    private BufferedImage realTarget;

    public LisaFitnessEvaluator(Chromosome target, BufferedImage realTarget) {
        this.target = target;
        this.realTarget = realTarget;
        this.current = new BufferedImage(realTarget.getWidth(), realTarget.getHeight(), BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    public float score(Chromosome c) {

        this.current = new BufferedImage(realTarget.getWidth(), realTarget.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = this.current.createGraphics();
        drawIntoImage((Graphics2D) g, c);

        float fitness = 0;

        for (int x = 0; x < current.getWidth(); x++) {
            for (int y = 0; y < current.getHeight(); y++) {
                Color c1 = new Color(current.getRGB(x, y));
                Color c2 = new Color(realTarget.getRGB(x, y));

                fitness += Math.abs(c1.getRed() - c2.getRed());
                fitness += Math.abs(c1.getGreen() - c2.getGreen());
                fitness += Math.abs(c1.getBlue() - c2.getBlue());

            }

        }

        return 1 - (fitness / getMaximumScore());
    }

    private void drawIntoImage(Graphics2D g, Chromosome c) {

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, current.getWidth(), current.getWidth());

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (Gene gene : c) {
            LisaGene l = (LisaGene) gene;
            float alpha = (float) l.getColor().getAlpha() / 256;
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g.setColor(l.getColor());
            g.fillPolygon(l.getPolygon());
        }
    }

    @Override
    public float getMaximumScore() {
        return (255 * 3) * (realTarget.getWidth() * realTarget.getHeight());
    }

    public BufferedImage getCurrent() {
        return current;
    }

}
