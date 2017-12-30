package examples.example_monalisa.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

    private BufferedImage image;

    public ImagePanel(BufferedImage image) {
        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);

        grphcs.drawImage(image, 0, 0, null);
        grphcs.setColor(Color.BLACK);
        grphcs.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }

}
