package genetic;

import genetic.element.Chromosome;

public interface FitnessEvaluator {

    public float score(Chromosome c);
    public float getMaximumScore();
    
}
