package examples.example_string;

import genetic.element.Chromosome;
import genetic.element.Gene;
import java.util.ArrayList;

public class StringChromosome extends Chromosome{

    public StringChromosome() {
        super();
    }
    
    public StringChromosome(float fitness, ArrayList<Gene> genes) {
        super(fitness, genes);
    }
    
    @Override
    public String toString() {
       String str = "";
       
       for(Gene gene : this){
           str += gene.toString();
       }
       
       return str;
    }

    @Override
    public boolean equals(Object o) {
        
        if(!(o instanceof StringChromosome))
            return false;
        
        StringChromosome c = (StringChromosome) o;
        
        return c.toString().equals(toString());
        
    }

    
    
    
}
