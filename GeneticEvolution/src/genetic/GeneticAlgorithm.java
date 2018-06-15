package genetic;

import genetic.element.Population;
import genetic.element.Chromosome;
import genetic.strategy.SelectionStrategy;
import genetic.strategy.MutationStrategy;
import genetic.strategy.CrossoverStrategy;
import genetic.factory.GeneFactory;
import genetic.factory.ChromosomeFactory;
import genetic.strategy.EvolutionStrategy;
import java.util.Observable;

public class GeneticAlgorithm extends Observable {

    private Population population;

    private GeneFactory geneFactory;
    private ChromosomeFactory chromosomeFactory;

    private FitnessEvaluator evaluator;

    private SelectionStrategy selectionStrategy;
    private CrossoverStrategy crossoverStrategy;
    private MutationStrategy mutationStrategy;
    private EvolutionStrategy evolutionStrategy;

    public void start(Chromosome target, int maxPopulationSize, float mutationRate) {
        
        population = new Population(target, maxPopulationSize);
        population.setChromosomeFactory(chromosomeFactory);
        population.setGeneFactory(geneFactory);
        population.setSelectionStrategy(selectionStrategy);
        population.setMutationStrategy(mutationStrategy);
        population.setCrossoverStrategy(crossoverStrategy);
        population.setEvaluator(evaluator);
        population.setEvolutionStrategy(evolutionStrategy);

        population.seed();

        while (!population.hasEvolved()) {
            if (population.evolve()) {
                updateObservers();
            }
        }

    }

    private void updateObservers() {
        setChanged();
        notifyObservers(population);
        clearChanged();
    }

    public Population getPopulation() {
        return population;
    }

    public void setGeneFactory(GeneFactory geneFactory) {
        this.geneFactory = geneFactory;
    }

    public void setChromosomeFactory(ChromosomeFactory chromosomeFactory) {
        this.chromosomeFactory = chromosomeFactory;
    }

    public void setEvaluator(FitnessEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    public void setSelectionStrategy(SelectionStrategy selectionStrategy) {
        this.selectionStrategy = selectionStrategy;
    }

    public void setCrossoverStrategy(CrossoverStrategy crossoverStrategy) {
        this.crossoverStrategy = crossoverStrategy;
    }

    public void setMutationStrategy(MutationStrategy mutationStrategy) {
        this.mutationStrategy = mutationStrategy;
    }

    public void setEvolutionStrategy(EvolutionStrategy evolutionStrategy) {
        this.evolutionStrategy = evolutionStrategy;
    }

    public GeneFactory getGeneFactory() {
        return geneFactory;
    }

    public ChromosomeFactory getChromosomeFactory() {
        return chromosomeFactory;
    }

    public FitnessEvaluator getEvaluator() {
        return evaluator;
    }

    public SelectionStrategy getSelectionStrategy() {
        return selectionStrategy;
    }

    public CrossoverStrategy getCrossoverStrategy() {
        return crossoverStrategy;
    }

    public MutationStrategy getMutationStrategy() {
        return mutationStrategy;
    }

}
