package examples.example_string;

import genetic.element.Chromosome;
import genetic.element.GeneticPair;
import genetic.element.Population;
import genetic.strategy.SelectionStrategy;
import java.util.Random;

public class StringSelectionStrategy implements SelectionStrategy {

    private final float maxFitness;
    private final Random random;

    public StringSelectionStrategy(float maxFitness) {
        this.random = new Random();
        this.maxFitness = maxFitness;
    }

    @Override
    public GeneticPair select(Population population) {

        Chromosome first = null;
        Chromosome second = null;

        float totalFitness = 0;

        for (Chromosome c : population) {
            totalFitness += c.getFitness();
        }

        if (totalFitness == 0) {
            
            int randomIndexA = random.nextInt(population.size());
            int randomIndexB = random.nextInt(population.size());
            
            while (randomIndexB == randomIndexA) {
                randomIndexB = random.nextInt(population.size());
            }
            
            return new GeneticPair(population.get(randomIndexA), population.get(randomIndexB));
        }

        while (first == null || second == null) {
            for (Chromosome c : population) {

                if (c.getFitness() == 0) {
                    continue;
                }

                float chance = (c.getFitness() / maxFitness) / 10;
                float rnd = random.nextFloat();

                if (rnd < chance) {
                    if (first == null) {
                        first = c;
                    } else {
                        second = c;
                        return new GeneticPair(first, second);
                    }
                }
            }
        }

        return null;
    }

}
