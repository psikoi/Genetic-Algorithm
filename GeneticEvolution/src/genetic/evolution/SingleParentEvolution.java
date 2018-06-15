package genetic.evolution;

import genetic.element.Chromosome;
import genetic.element.Population;
import genetic.strategy.EvolutionStrategy;

public class SingleParentEvolution implements EvolutionStrategy {

    @Override
    public boolean evolve(Population population) {

        Population newPopulation = population.copy();

        for (Chromosome c : newPopulation) {
            newPopulation.getMutationStrategy().mutate(c);
        }

        newPopulation.evaluate();

        if (newPopulation.getTotalFitness() > population.getTotalFitness()) {
            population.clear();
            population.addAll(newPopulation);
            population.increaseBeneficialGenerations();
            return true;
        }

        return true;
    }

}
