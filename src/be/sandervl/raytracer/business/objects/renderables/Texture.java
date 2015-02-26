package be.sandervl.raytracer.business.objects.renderables;

import be.sandervl.raytracer.business.math.MathUtils;
import be.sandervl.raytracer.business.math.Vector3D;
import be.sandervl.raytracer.business.scene.Color;

import java.awt.image.BufferedImage;

/**
 * Created by sander on 25/02/2015.
 */
public class Texture {
    private BufferedImage image;

    public Texture(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public Color getPoint(Vector3D vt) {
        float x = vt.getX() * image.getWidth();
        float y = vt.getY() * image.getHeight();
        int rgb = image.getRGB((int)MathUtils.round(0,image.getWidth()-1,x),(int)MathUtils.round(0,image.getHeight()-1,y));
        java.awt.Color tColor = new java.awt.Color(rgb);
        return new Color((float)tColor.getRed()/255,(float)tColor.getGreen()/255,(float)tColor.getBlue()/255);
    }
}
