package genetic.strategy;

import genetic.element.Population;

public interface EvolutionStrategy {

    public boolean evolve(Population population);
    
}
