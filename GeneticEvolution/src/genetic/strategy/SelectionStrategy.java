package genetic.strategy;

import genetic.element.GeneticPair;
import genetic.element.Population;

public interface SelectionStrategy {

    public GeneticPair select(Population population);
    
}
