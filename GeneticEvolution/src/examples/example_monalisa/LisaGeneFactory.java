package examples.example_monalisa;

import genetic.element.Gene;
import genetic.factory.GeneFactory;
import examples.example_monalisa.elements.CPolygon;
import java.awt.Color;
import java.util.Random;

public class LisaGeneFactory implements GeneFactory {

    private int screenSize;
    private Random random;

    public LisaGeneFactory(int screenSize) {
        this.screenSize = screenSize;
        this.random = new Random();
    }

    @Override
    public Gene generateRandom() {

        int randomRed = random.nextInt(255);
        int randomBlue = random.nextInt(255);
        int randomGreen = random.nextInt(255);
        int randomAlpha = random.nextInt(255);

        Color rndColor = new Color(randomRed, randomGreen, randomBlue, randomAlpha);
        CPolygon poly = new CPolygon();

        for (int i = 0; i < 6; i++)  {

            int randomX = random.nextInt(screenSize);
            int randomY = random.nextInt(screenSize);
            
            poly.addPoint(randomX, randomY);
            
        }

        return new LisaGene(poly, rndColor);

    }

}
