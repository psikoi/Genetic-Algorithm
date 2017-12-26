package genetic.evolution;

import genetic.element.Chromosome;
import genetic.element.Population;
import genetic.strategy.EvolutionStrategy;

public class SingleParentEvolution implements EvolutionStrategy {

    @Override
    public boolean evolve(Population population) {

        boolean evolved = false;

        Population newPopulation = new Population(population.getTarget(), population.getMaxPopulation());
        newPopulation.setEvaluator(population.getEvaluator());

        
        Chromosome c = new Chromosome();
        c.addAll(population.getFittest());
        c.setFitness(population.getFittest().getFitness());

        population.getMutationStrategy().mutate(c);
        newPopulation.add(c);

        
        newPopulation.evaluate();
        

        if (c.getFitness() > population.getFittest().getFitness()) {
            population.clear();
            population.addAll(newPopulation);
            population.evaluate();
            evolved = true;
        }
        

        return evolved;

    }

}
