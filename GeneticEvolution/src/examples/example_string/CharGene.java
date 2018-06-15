package examples.example_string;

import genetic.element.Gene;

public class CharGene implements Gene {

    private char data;

    public CharGene(char data) {
        this.data = data;
    }

    public char getData() {
        return data;
    }

    @Override
    public String toString() {
        return data + "";
    }
    
    @Override
    public boolean equals(Object o) {
        
        if(!(o instanceof CharGene))
            return false;
        
        CharGene c = (CharGene) o;
        
        return c.getData() == getData();
    }

    @Override
    public Gene copy() {
        return new CharGene(data);
    }

}
