package examples.example_monalisa.ui;

import examples.example_monalisa.LisaMutationStrategy;
import genetic.element.Population;
import java.awt.Graphics;
import javax.swing.JPanel;

public class StatsPanel extends JPanel {

    private Population population;
    private long start;

    public StatsPanel() {
        start = System.currentTimeMillis();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (population != null && population.getFittest() != null) {

            int secondsElapsed = (int) (System.currentTimeMillis() - start) / 1000;
            int improvements = population.getBeneficialGenerations();
            int generations = population.getGeneration();
            
            //secondsElapsed *= 600;
            //secondsElapsed += 5;

            String elapsed = "";
            if (secondsElapsed < 60) {
                elapsed = secondsElapsed + "s";
            } else if (secondsElapsed < 3600) {
                int mins = secondsElapsed / 60;
                int secs = secondsElapsed % 60;
                elapsed = mins + "m " + secs + "s";
            } else {
                int hours = secondsElapsed / 60 / 60;
                int mins = secondsElapsed / 60 % 60;
                int secs = secondsElapsed / 60 % 60;
                elapsed = hours + "h " + mins + "m " + secs + "s"; //wroooooong
            }

            int improvementsPerSecond = 0;

            if (improvements != 0 && secondsElapsed != 0) {
                improvementsPerSecond = improvements / secondsElapsed;
            }

            int generationsPerSecond = 0;

            if (generations != 0 && secondsElapsed != 0) {
                generationsPerSecond = generations / secondsElapsed;
            }

            LisaMutationStrategy mutStrat = (LisaMutationStrategy) population.getMutationStrategy();

            g.drawString("Elapsed time: " + elapsed, 0, 10);
            g.drawString("Generations: " + generations + " (" + generationsPerSecond + " p/s)", 0, 30);
            g.drawString("Improvements: " + improvements + " (" + improvementsPerSecond + " p/s)", 0, 50);
            g.drawString("Fitness: " + String.format("%.2f", (double) (population.getFittest().getFitness() * 100)) + "%", 0, 70);

            g.drawString("Population size: " + population.getMaxPopulation(), 0, 110);
            g.drawString("Mutation strength: " + mutStrat.getStrength(), 0, 130);
        }

    }

    public void update(Population p) {
        this.population = p;
        repaint();

    }
}
