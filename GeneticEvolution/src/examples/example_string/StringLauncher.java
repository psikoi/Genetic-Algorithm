package examples.example_string;

import examples.example_monalisa.*;
import examples.example_monalisa.LisaChromosomeFactory;
import examples.example_monalisa.LisaFitnessEvaluator;
import examples.example_monalisa.LisaGeneFactory;
import examples.example_monalisa.LisaMutationStrategy;
import examples.example_monalisa.LisaMutationStrategy.MutationStrength;
import examples.example_monalisa.ui.*;
import examples.example_string.ui.StringExampleFrame;
import examples.example_string.ui.StringExamplePanel;
import genetic.GeneticAlgorithm;
import genetic.element.Chromosome;
import genetic.element.Gene;
import genetic.evolution.DoubleParentEvolution;
import genetic.evolution.SingleParentEvolution;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class StringLauncher {

    private static final int MAX_POPULATION = 200;
    private static final float MUTATION_RATE = 0.01f;

    private static GeneticAlgorithm gen;

    private static StringExamplePanel panel;

    public static void main(String[] args) {

        Chromosome target = convert("I was coded by Ruben Amendoeira");

        initUI();

        gen = new GeneticAlgorithm();
        gen.setGeneFactory(new CharGeneFactory());
        gen.setChromosomeFactory(new StringChromosomeFactory(target.size(), gen.getGeneFactory()));
        gen.setEvaluator(new StringFitnessEvaluator(target));
        gen.setSelectionStrategy(new StringSelectionStrategy(gen.getEvaluator().getMaximumScore()));
        gen.setCrossoverStrategy(new StringCrossoverStrategy());
        gen.setMutationStrategy(new StringMutationStrategy((CharGeneFactory) gen.getGeneFactory(), MUTATION_RATE));
        gen.setEvolutionStrategy(new DoubleParentEvolution());

        gen.addObserver(panel);

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

    private static Chromosome convert(String s) {
        char[] a = s.toCharArray();
        ArrayList<Gene> genes = new ArrayList<>();
        for (char c : a) {
            genes.add(new CharGene(c));
        }
        return new StringChromosome(0, genes);
    }

    public static void initUI() {
        StringExampleFrame frame = new StringExampleFrame();
        panel = new StringExamplePanel();
        frame.setContentPane(panel);
        frame.setVisible(true);
    }

}
