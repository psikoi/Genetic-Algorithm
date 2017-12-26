package examples.example_string;

import genetic.element.Chromosome;
import genetic.FitnessEvaluator;

public class StringFitnessEvaluator implements FitnessEvaluator {

    private Chromosome target;

    public StringFitnessEvaluator(Chromosome target) {
        this.target = target;
    }

    @Override
    public float score(Chromosome c) {

        int score = 0;

        for (int i = 0; i < target.size(); i++) {

            if (target.get(i).equals(c.get(i))) {
                score++;
            }

        }

        return (float) Math.pow(score, 3);

    }

    @Override
    public float getMaximumScore() {
        return (float) Math.pow(target.size(), 3);
    }
}
