package examples.example_string;

import genetic.element.Chromosome;
import genetic.factory.ChromosomeFactory;
import genetic.factory.GeneFactory;

public class StringChromosomeFactory implements ChromosomeFactory {

    private final int geneCount;
    private final GeneFactory geneFactory;

    public StringChromosomeFactory(int geneCount, GeneFactory geneFactory) {
        this.geneCount = geneCount;
        this.geneFactory = geneFactory;
    }

    @Override
    public Chromosome generateRandom() {

        StringChromosome c = new StringChromosome();

        for (int i = 0; i < geneCount; i++) {
            c.add(geneFactory.generateRandom());
        }

        return c;
    }

}
