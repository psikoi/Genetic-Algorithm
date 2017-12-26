package genetic.strategy;

import genetic.element.Chromosome;

public interface CrossoverStrategy {

    public Chromosome crossover(Chromosome a, Chromosome b);
    
}
