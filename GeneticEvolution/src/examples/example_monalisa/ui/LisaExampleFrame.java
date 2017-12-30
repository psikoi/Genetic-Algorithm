package examples.example_monalisa.ui;

import genetic.element.Population;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LisaExampleFrame extends JFrame implements Observer {

    private JPanel container;
    private ImagePanel imgPanel;
    private GeneratorPanel genPanel;
    private StatsPanel statsPanel;

    private final int MARGIN = 10;
    private final int X_OFFSET = 15;
    private final int Y_OFFSET = 37;

    public LisaExampleFrame(int screenSize, BufferedImage targetImage) {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(X_OFFSET + ((MARGIN + screenSize) * 3), Y_OFFSET + ((MARGIN + screenSize) * 2)));

        container = new JPanel();
        imgPanel = new ImagePanel(targetImage);
        genPanel = new GeneratorPanel();
        statsPanel = new StatsPanel();

        container.setLayout(null);
        container.setBounds(getBounds());

        imgPanel.setBounds(MARGIN, MARGIN, screenSize, screenSize);
        genPanel.setBounds(MARGIN * 2 + screenSize, MARGIN, screenSize * 2, screenSize * 2);
        statsPanel.setBounds(MARGIN, (MARGIN * 2) + screenSize, screenSize, screenSize - MARGIN);

        container.add(genPanel);
        container.add(imgPanel);
        container.add(statsPanel);
        
        setContentPane(container);

    }

    @Override
    public void update(Observable o, Object o1) {
        
        if(!(o1 instanceof Population))
            return;
        
        Population p = (Population) o1;
        
        genPanel.update(p);
        statsPanel.update(p);
        
    }

}
