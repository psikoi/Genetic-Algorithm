package examples.example_string.ui;

import genetic.element.Chromosome;
import genetic.element.Population;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

public class StringExamplePanel extends JPanel implements Observer {

    private Population population;
    private final Font bold;

    private int generationCount;

    public StringExamplePanel() {
        this.bold = new Font("Arial", 1, 13);
        this.generationCount = 0;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (population == null) {
            return;
        }

        g.drawString(population.getTarget() + "", 20, 45);

        Chromosome fittest = population.getFittest();
        if (fittest != null) {
            g.drawString(fittest + "", 20, 95);
            g.drawString((int) population.getFitnessPercent(fittest) + "%", 20, 145);
        }

        g.drawString((int) population.averageFitnessPercent() + "%", 20, 195);
        g.drawString(population.getGeneration() + "", 20, 245);
        g.drawString(population.size() + "", 20, 295);

        if (population.getMutationStrategy() != null) {
            g.drawString(population.getMutationStrategy().getMutationRate() * 100 + "%", 20, 345);
        }

        g.setFont(bold);
        g.drawString("Target text:", 20, 25);
        g.drawString("Best chromosome:", 20, 75);
        g.drawString("Best fitness:", 20, 125);
        g.drawString("Average fitness:", 20, 175);
        g.drawString("Generations:", 20, 225);
        g.drawString("Population size:", 20, 275);
        g.drawString("Mutation rate:", 20, 325);

    }

    @Override
    public void update(Observable o, Object o1) {

        generationCount++;

        Population p = (Population) o1;

        if (generationCount % 10 == 0 || p.hasEvolved()) {

            this.population = new Population(p.getTarget(), p.getMaxPopulation());
            this.population.addAll(p);
            this.population.setEvaluator(p.getEvaluator());
            this.population.setGeneration(p.getGeneration());
            this.population.setMutationStrategy(p.getMutationStrategy());

            repaint();
        }
    }

}
