package examples.example_string;

import genetic.element.Chromosome;
import genetic.element.Gene;
import genetic.strategy.MutationStrategy;
import java.util.Random;

public class StringMutationStrategy extends MutationStrategy {

    private final Random random;
    private final CharGeneFactory factory;

    public StringMutationStrategy(CharGeneFactory factory, float mutationRate) {
        super(mutationRate);
        this.factory = factory;
        this.random = new Random();
    }

    @Override
    public Chromosome mutate(Chromosome c) {

        for (int i = 0; i < c.size(); i++) {
            if (random.nextFloat() < getMutationRate()) {
                Gene toMutate = c.remove(i);
                c.add(i, factory.generateRandom());
            }
        }

        return c;
    }

}
