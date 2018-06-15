package examples.example_monalisa;

import genetic.element.Chromosome;
import genetic.factory.ChromosomeFactory;
import genetic.factory.GeneFactory;
import java.util.Random;

public class LisaChromosomeFactory implements ChromosomeFactory {

    private final int geneCount;
    private final GeneFactory geneFactory;
    private final Random random;

    public LisaChromosomeFactory(int geneCount, GeneFactory geneFactory) {
        this.geneCount = geneCount;
        this.geneFactory = geneFactory;
        this.random = new Random();
    }

    @Override
    public Chromosome generateRandom() {

        LisaChromosome c = new LisaChromosome();

        for (int i = 0; i < geneCount; i++) {
            c.add(geneFactory.generateRandom());
        }

        return c;

    }

}
