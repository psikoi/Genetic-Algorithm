package genetic.strategy;

import genetic.element.Chromosome;

public abstract class MutationStrategy {

    private float mutationRate;

    public MutationStrategy(float mutationRate) {
        this.mutationRate = mutationRate;
    }

    public float getMutationRate() {
        return mutationRate;
    }
    
    public abstract Chromosome mutate(Chromosome a);
    
}
