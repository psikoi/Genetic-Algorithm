package examples.example_string;

import genetic.element.Chromosome;
import genetic.factory.ChromosomeFactory;
import genetic.factory.GeneFactory;
import java.util.Random;

public class StringChromosomeFactory implements ChromosomeFactory {

        private int geneCount;
        private GeneFactory geneFactory;
        private Random random;

        public StringChromosomeFactory(int geneCount, GeneFactory geneFactory) {
            this.geneCount = geneCount;
            this.geneFactory = geneFactory;
            this.random = new Random();
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
