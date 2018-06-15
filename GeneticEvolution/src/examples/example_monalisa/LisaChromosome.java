package examples.example_monalisa;

import genetic.element.Chromosome;
import genetic.element.Gene;
import java.util.ArrayList;

public class LisaChromosome extends Chromosome {

    public LisaChromosome() {
        super();
    }

    public LisaChromosome(float fitness, ArrayList<Gene> genes) {
        super(fitness, genes);
    }

    @Override
    public String toString() {
        
        String str = "";
        
        for(Gene g : this){
            str += g.toString();
        }
        
        return str;
    }
    
}
