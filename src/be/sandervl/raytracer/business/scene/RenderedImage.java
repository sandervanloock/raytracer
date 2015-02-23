package be.sandervl.raytracer.business.scene;

import javax.swing.*;
import java.awt.*;

/**
 * Created by sander on 22/02/2015.
 */
public class RenderedImage extends JComponent {
    private Color[][] pixels;

    public RenderedImage(Color[][] pixels) {
        this.pixels = pixels;
    }

    public void paintComponent(Graphics g) {
        for (int y = 0; y < pixels[0].length; y++) {
            for (int x = 0; x < pixels.length; x++) {
                Rectangle box = new Rectangle(x, y, 1, 1);
                Color color = pixels[x][y];
                g.setColor(new java.awt.Color(color.getR(), color.getG(), color.getB()));
                g.drawLine(x, y, x, y);
            }
        }
    }

    public void setPixel(int i, int j, Color color){
        this.pixels[i][j] = color;
        this.repaint();
    }

    public void setPixels(Color[][] pixels) {
        this.pixels = pixels;
        this.repaint();
    }

    public Color[][] getPixels() {
        return pixels;
    }
}
