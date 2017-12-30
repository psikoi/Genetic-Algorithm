package examples.example_string;

import genetic.element.Gene;
import genetic.factory.GeneFactory;
import java.util.Random;

public class CharGeneFactory implements GeneFactory {

    private Random random;

    public CharGeneFactory() {
        this.random = new Random();
    }

    private final String POSSIBLE_CHARS = ".,!? qwertyuiopasdghjklçzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";

    @Override
    public Gene generateRandom() {
        return new CharGene(POSSIBLE_CHARS.charAt(random.nextInt(POSSIBLE_CHARS.length())));
    }

}
