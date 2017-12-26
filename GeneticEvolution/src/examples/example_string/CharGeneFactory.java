package examples.example_string;

import genetic.element.Gene;
import genetic.factory.GeneFactory;
import java.util.Random;

  public class CharGeneFactory implements GeneFactory {

        private final String POSSIBLE_CHARS = ".,!? qwertyuiopasdghjklçzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";

        @Override
        public Gene generateRandom() {
            return new CharGene(POSSIBLE_CHARS.charAt(new Random().nextInt(POSSIBLE_CHARS.length())));
        }

    }