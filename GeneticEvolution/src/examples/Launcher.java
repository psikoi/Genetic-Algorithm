package examples;

import genetic.GeneticAlgorithm;
import genetic.element.Chromosome;
import genetic.element.Gene;
import genetic.evolution.DoubleParentEvolution;
import examples.example_string.CharGene;
import examples.example_string.CharGeneFactory;
import examples.example_string.StringChromosome;
import examples.example_string.StringChromosomeFactory;
import examples.example_string.StringCrossoverStrategy;
import examples.example_string.StringFitnessEvaluator;
import examples.example_string.StringMutationStrategy;
import examples.example_string.StringSelectionStrategy;
import examples.example_string.ui.StringExampleFrame;
import examples.example_string.ui.StringExamplePanel;
import java.util.ArrayList;

public class Launcher {

    public static void main(String[] args) {

        int maxPopulation = 200;
        float mutationRate = 0.01f;
        Chromosome target = convert("Hello, my name is Ruben.");

        StringExampleFrame frame = new StringExampleFrame();
        StringExamplePanel panel = new StringExamplePanel();
        frame.setContentPane(panel);
        frame.setVisible(true);

        GeneticAlgorithm gen = new GeneticAlgorithm();
        gen.setGeneFactory(new CharGeneFactory());
        gen.setChromosomeFactory(new StringChromosomeFactory(target.size(), gen.getGeneFactory()));
        gen.setEvaluator(new StringFitnessEvaluator(target));
        gen.setSelectionStrategy(new StringSelectionStrategy(gen.getEvaluator().getMaximumScore()));
        gen.setCrossoverStrategy(new StringCrossoverStrategy());
        gen.setMutationStrategy(new StringMutationStrategy((CharGeneFactory) gen.getGeneFactory(), mutationRate));
        gen.setEvolutionStrategy(new DoubleParentEvolution());

        gen.addObserver(panel);
        
        gen.start(target, maxPopulation, mutationRate);

    }

    private static Chromosome convert(String s) {
        char[] a = s.toCharArray();
        ArrayList<Gene> genes = new ArrayList<>();
        for (char c : a) {
            genes.add(new CharGene(c));
        }
        return new StringChromosome(0, genes);
    }

}
