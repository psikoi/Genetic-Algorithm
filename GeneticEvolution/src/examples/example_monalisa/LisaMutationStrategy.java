package examples.example_monalisa;

import genetic.element.Chromosome;
import genetic.element.Gene;
import genetic.factory.GeneFactory;
import genetic.strategy.MutationStrategy;
import java.awt.Color;
import java.util.Random;

public class LisaMutationStrategy extends MutationStrategy {

    private final Random random;
    private final int screenSize;

    private final GeneFactory geneFactory;

    private final MutationStrength selectedStrength;
    private MutationStrength currentStrength;

    private final long start;
    private long lastRandomAddition;

    public LisaMutationStrategy(MutationStrength selectedStrength, int screenSize, GeneFactory geneFactory) {
        super(1f);
        this.geneFactory = geneFactory;
        this.random = new Random();
        this.screenSize = screenSize;
        this.selectedStrength = selectedStrength;
        this.currentStrength = MutationStrength.HARD;
        this.start = System.currentTimeMillis();
        this.lastRandomAddition = System.currentTimeMillis();
    }

    @Override
    public Chromosome mutate(Chromosome a) {

        int randIndex = random.nextInt(a.size());

        Gene toMutate = a.remove(randIndex);
        a.add(randIndex, mutated(a, toMutate));

        return a;
    }

    private Gene mutated(Chromosome a, Gene gene) {

        LisaGene g = (LisaGene) gene;

        switch (selectedStrength) {
            case HARD:
                mutateHard(g);
                break;
            case MEDIUM:
                mutateMedium(g);
                break;
            case SOFT:
                mutateSoft(g);
                break;
            case GAUSSIAN:
                mutateGaussian(g);
                break;
            case OPTIMAL:
                mutateOptimal(g, a);
                break;
        }

        return g;
    }

    private void mutateOptimal(LisaGene g, Chromosome a) {

        long elapsed = (System.currentTimeMillis() - start);

        if (currentStrength.equals(MutationStrength.HARD) && elapsed > 5000) {
            currentStrength = MutationStrength.MEDIUM;
        } else if (currentStrength.equals(MutationStrength.MEDIUM) && elapsed > 60000) {
            currentStrength = MutationStrength.SOFT;
        }

        switch (currentStrength) {
            case HARD:
                mutateHard(g);
                break;
            case MEDIUM:
                mutateMedium(g);
                break;
            case SOFT:
                mutateSoft(g);
                break;
        }
        
        if(System.currentTimeMillis() > (lastRandomAddition + 10000)){
            lastRandomAddition = System.currentTimeMillis();
            a.add(geneFactory.generateRandom());
        }
       
    }

    private void mutateGaussian(LisaGene g) {

        float actionChance = random.nextFloat() * 2;

        if (actionChance < 1) {

            int red = g.getColor().getRed();
            int green = g.getColor().getGreen();
            int blue = g.getColor().getBlue();
            int alpha = g.getColor().getAlpha();

            if (actionChance < 0.25) {
                double delta = random.nextGaussian() * red * 0.1;
                g.setColor(new Color(limit(red + delta, 255, 0), green, blue, alpha));
            } else if (actionChance < 0.5) {
                double delta = (int) random.nextGaussian() * green * 0.2;
                g.setColor(new Color(red, limit(green + delta, 255, 0), blue, alpha));
            } else if (actionChance < 0.75) {
                double delta = (int) random.nextGaussian() * blue * 0.2;
                g.setColor(new Color(red, green, limit(blue + delta, 255, 0), alpha));
            } else {
                double delta = (int) random.nextGaussian() * alpha * 0.2;
                g.setColor(new Color(red, green, blue, limit(alpha + delta, 255, 0)));
            }
        } else {
            int pointIndex = random.nextInt(g.getPolygon().npoints);

            if (actionChance < 1.5) {
                double delta = (int) random.nextGaussian() * g.getPolygon().xpoints[pointIndex] * 0.2;
                g.getPolygon().xpoints[pointIndex] = limit(g.getPolygon().xpoints[pointIndex] + delta, screenSize, 0);
            } else {
                double delta = (int) random.nextGaussian() * g.getPolygon().ypoints[pointIndex] * 0.2;
                g.getPolygon().ypoints[pointIndex] = limit(g.getPolygon().ypoints[pointIndex] + delta, screenSize, 0);
            }
        }
        
    }

    private void mutateHard(LisaGene g) {

        int pointIndex = random.nextInt(g.getPolygon().npoints);
        
        g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255), random.nextInt(255)));
        g.getPolygon().xpoints[pointIndex] = random.nextInt(200);
        g.getPolygon().ypoints[pointIndex] = random.nextInt(200);

    }

    private void mutateMedium(LisaGene g) {

        float actionChance = random.nextFloat() * 2;

        if (actionChance < 1) {

            int red = g.getColor().getRed();
            int green = g.getColor().getGreen();
            int blue = g.getColor().getBlue();
            int alpha = g.getColor().getAlpha();

            if (actionChance < 0.25) {
                g.setColor(new Color(random.nextInt(255), green, blue, alpha));
            } else if (actionChance < 0.5) {
                g.setColor(new Color(red, random.nextInt(255), blue, alpha));
            } else if (actionChance < 0.75) {
                g.setColor(new Color(red, green, random.nextInt(255), alpha));
            } else {
                g.setColor(new Color(red, green, blue, random.nextInt(255)));
            }
            
        } else {
            
            int pointIndex = random.nextInt(g.getPolygon().npoints);

            if (actionChance < 1.5) {
                g.getPolygon().xpoints[pointIndex] = random.nextInt(screenSize);
            } else {
                g.getPolygon().ypoints[pointIndex] = random.nextInt(screenSize);
            }
            
        }
    }

    private void mutateSoft(LisaGene g) {

        int delta = -softMutationOffset() + random.nextInt(softMutationOffset() * 2);
        float actionChance = random.nextFloat() * 2;

        if (actionChance < 1) {

            int red = g.getColor().getRed();
            int green = g.getColor().getGreen();
            int blue = g.getColor().getBlue();
            int alpha = g.getColor().getAlpha();

            if (actionChance < 0.25) {
                g.setColor(new Color(limit(red + delta, 255, 0), green, blue, alpha));
            } else if (actionChance < 0.5) {
                g.setColor(new Color(red, limit(green + delta, 255, 0), blue, alpha));
            } else if (actionChance < 0.75) {
                g.setColor(new Color(red, green, limit(blue + delta, 255, 0), alpha));
            } else {
                g.setColor(new Color(red, green, blue, limit(alpha + delta, 255, 0)));
            }

        } else {
            
            int pointIndex = random.nextInt(g.getPolygon().npoints);

            if (actionChance < 1.5) {
                g.getPolygon().xpoints[pointIndex] = limit(g.getPolygon().xpoints[pointIndex] + delta, screenSize, 0);
            } else {
                g.getPolygon().ypoints[pointIndex] = limit(g.getPolygon().ypoints[pointIndex] + delta, screenSize, 0);
            }

        }
    }

    private int limit(int num, int max, int min) {

        if (num > max) {
            num = max;
        }

        if (num < min) {
            num = min;
        }

        return num;
    }

    private int limit(double num, int max, int min) {

        if (num > max) {
            num = max;
        }

        if (num < min) {
            num = min;
        }

        return (int) num;
    }

    public MutationStrength getStrength() {
        return selectedStrength;
    }

    private int softMutationOffset() {

        if (selectedStrength.equals(MutationStrength.OPTIMAL)) {

            long elapsed = (System.currentTimeMillis() - start);

            if (elapsed > 1000) {
                int reduce = (int) ((elapsed / 1000) / 180);
                if (reduce > 22) {
                    reduce = 22;
                }
                return 25 - reduce;
            }
        }

        return 25;
    }

    public enum MutationStrength {
        GAUSSIAN, SOFT, MEDIUM, HARD, OPTIMAL;
    }

}
