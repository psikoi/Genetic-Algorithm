package genetic.element;

import java.util.ArrayList;

public class Chromosome extends ArrayList<Gene>{

    private float fitness;
    
    public Chromosome() {
        super();
        this.fitness = 0;
    }

    public Chromosome(float fitness, ArrayList<Gene> genes){
        super(genes);
        this.fitness = fitness;
    }

    public float getFitness() {
        return fitness;
    }

    public void setFitness(float fitness) {
        this.fitness = fitness;
    }

    
    
    
    
}
