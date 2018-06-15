package genetic.evolution;

import genetic.element.Chromosome;
import genetic.element.GeneticPair;
import genetic.element.Population;
import genetic.strategy.EvolutionStrategy;

public class DoubleParentEvolution implements EvolutionStrategy {

    @Override
    public boolean evolve(Population population) {

        Population newPopulation = new Population(population.getTarget(), population.getMaxPopulation());

        for (int i = 0; i < population.getMaxPopulation(); i++) {

            GeneticPair parents = population.getSelectionStrategy().select(population);

            Chromosome c = population.getCrossoverStrategy().crossover(parents.getParent1(), parents.getParent2());

            population.getMutationStrategy().mutate(c);

            newPopulation.add(c);
        }

        population.clear();
        population.addAll(newPopulation);
        population.evaluate();
        
        return true;
    }

}
