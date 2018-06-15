package genetic.element;

public class GeneticPair {

    private final Chromosome parent1;
    private final Chromosome parent2;

    public GeneticPair(Chromosome parent1, Chromosome parent2) {
        this.parent1 = parent1;
        this.parent2 = parent2;
    }

    public Chromosome getParent1() {
        return parent1;
    }

    public Chromosome getParent2() {
        return parent2;
    }

}
