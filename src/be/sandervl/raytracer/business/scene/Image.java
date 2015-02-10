package be.sandervl.raytracer.business.scene;

public class Image {
    private final Color[][] pixels;
    private final int width,height;

    public Image(int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = new Color[width][height];
    }

    public Image(Color[][] pixels) {
        this.pixels = pixels;
        this.width = pixels.length;
        this.height = pixels[0].length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color[][] getPixels() {
        return pixels;
    }
}
