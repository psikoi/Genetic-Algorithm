package genetic.element;

import genetic.strategy.SelectionStrategy;
import genetic.strategy.MutationStrategy;
import genetic.strategy.CrossoverStrategy;
import genetic.factory.GeneFactory;
import genetic.factory.ChromosomeFactory;
import genetic.FitnessEvaluator;
import genetic.strategy.EvolutionStrategy;
import java.util.ArrayList;

public class Population extends ArrayList<Chromosome> {

    private int maxPopulation;
    private int generation;

    private Chromosome target;
    
    private GeneFactory geneFactory;
    private ChromosomeFactory chromosomeFactory;

    private FitnessEvaluator evaluator;

    private SelectionStrategy selectionStrategy;
    private CrossoverStrategy crossoverStrategy;
    private MutationStrategy mutationStrategy;
    private EvolutionStrategy evolutionStrategy;

    public Population(Chromosome target, int maxPopulation) {
        super();
        this.maxPopulation = maxPopulation;
        this.generation = 0;
        this.target = target;
    }

    public boolean evolve() {
        generation++;
        return evolutionStrategy.evolve(this);
    }

    public boolean hasEvolved() {
        return getFittest() != null && getFittest().getFitness() >= evaluator.getMaximumScore();
    }

    public float totalFitness() {

        if (evaluator == null) {
            return 0;
        }

        float fitness = 0;
        for (Chromosome c : this) {
            fitness += evaluator.score(c);
        }
        return fitness;
    }
    
    public float averageFitnessPercent(){
        
        if(evaluator == null)
            return 0;
        
        return ((totalFitness() / size()) / evaluator.getMaximumScore()) * 100;
    }
    
    public float averageFitness(){
        return totalFitness() / size();
    }
    
    public double getFitnessPercent(Chromosome c){
        
        if(c == null  || evaluator == null)
            return 0;
        
        return (c.getFitness() / evaluator.getMaximumScore()) * 100;
    }

    public void evaluate() {

        if (evaluator == null) {
            return;
        }

        for (Chromosome c : this) {
            c.setFitness(evaluator.score(c));
        }
    }

    public void seed() {
        for (int i = size(); i < maxPopulation; i++) {
            add(chromosomeFactory.generateRandom());
        }
    }

    public Chromosome getFittest() {

        Chromosome best = null;

        for (Chromosome c : this) {
            if (best == null || c.getFitness() > best.getFitness()) {
                best = c;
            }
        }

        return best;

    }

    public Chromosome getWeakest() {

        Chromosome worst = null;

        for (Chromosome c : this) {
            if (worst == null || c.getFitness() < worst.getFitness()) {
                worst = c;
            }
        }

        return worst;

    }

    
    public void setEvaluator(FitnessEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    public void setGeneFactory(GeneFactory geneFactory) {
        this.geneFactory = geneFactory;
    }

    public void setChromosomeFactory(ChromosomeFactory chromosomeFactory) {
        this.chromosomeFactory = chromosomeFactory;
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

    public void setMaxPopulation(int maxPopulation) {
        this.maxPopulation = maxPopulation;
    }

    public int getGeneration() {
        return generation;
    }

    public int getMaxPopulation() {
        return maxPopulation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
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

    public FitnessEvaluator getEvaluator() {
        return evaluator;
    }

    public Chromosome getTarget() {
        return target;
    }
    
    
    
}
