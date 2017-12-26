package examples.example_string;

import genetic.element.Chromosome;
import genetic.strategy.CrossoverStrategy;
import java.util.Random;

public class StringCrossoverStrategy implements CrossoverStrategy {

    private Random random;

    public StringCrossoverStrategy() {
        this.random = new Random();
    }

    @Override
    public Chromosome crossover(Chromosome a, Chromosome b) {

        StringChromosome c = new StringChromosome();

        int half = a.size() / 2;

        for (int i = 0; i < half; i++) {
            c.add(a.get(i));
        }

        for (int i = half; i < a.size(); i++) {
            c.add(b.get(i));
        }

        return c;

    }

}
