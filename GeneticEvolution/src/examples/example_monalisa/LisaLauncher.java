package examples.example_monalisa;

import examples.example_monalisa.LisaChromosomeFactory;
import examples.example_monalisa.LisaFitnessEvaluator;
import examples.example_monalisa.LisaGeneFactory;
import examples.example_monalisa.LisaMutationStrategy;
import examples.example_monalisa.LisaMutationStrategy.MutationStrength;
import examples.example_monalisa.ui.*;
import genetic.GeneticAlgorithm;
import genetic.element.Chromosome;
import genetic.evolution.SingleParentEvolution;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class LisaLauncher {

    private static final int SCREEN_SIZE = 200;
    private static final int MAX_POPULATION = 1;
    private static final float MUTATION_RATE = 1f;
    private static final int GENE_COUNT = 50;
    private static final MutationStrength MUTATION_STRENGTH = MutationStrength.OPTIMAL;

    private static LisaExampleFrame frame;
    private static GeneticAlgorithm gen;

    public static void main(String[] args) {

        Chromosome target = null;
        BufferedImage targetImage = loadTargetImage("target.png");

        initUI(SCREEN_SIZE, targetImage);

        gen = new GeneticAlgorithm();
        gen.setGeneFactory(new LisaGeneFactory(SCREEN_SIZE));
        gen.setChromosomeFactory(new LisaChromosomeFactory(GENE_COUNT, gen.getGeneFactory()));
        gen.setEvaluator(new LisaFitnessEvaluator(target, targetImage));
        gen.setSelectionStrategy(null);
        gen.setCrossoverStrategy(null);
        gen.setMutationStrategy(new LisaMutationStrategy(MUTATION_STRENGTH, SCREEN_SIZE, gen.getGeneFactory()));
        gen.setEvolutionStrategy(new SingleParentEvolution());

        gen.addObserver(frame);

        gen.start(target, MAX_POPULATION, MUTATION_RATE);

    }

    private static BufferedImage loadTargetImage(String url) {
        try {
            return ImageIO.read(new File(url));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void initUI(int screenSize, BufferedImage targetImage) {
        frame = new LisaExampleFrame(screenSize, targetImage);
        frame.setVisible(true);
        frame.pack();
    }

}
