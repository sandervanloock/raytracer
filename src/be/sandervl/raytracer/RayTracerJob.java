package be.sandervl.raytracer;

import be.sandervl.raytracer.business.scene.Camera;
import be.sandervl.raytracer.business.scene.RenderedImage;
import be.sandervl.raytracer.business.scene.Scene;


/**
 * Created by sander on 16/02/2015.
 */
public class RayTracerJob implements Runnable {

    private final int startX, startY, width, height;
    private final Scene scene;
    private RayTracer rayTracer;
    private RenderedImage image;

    public RayTracerJob(int startX, int startY, int width, int height, Scene scene, Camera camera, RenderedImage image, RayTracer rayTracer) {
        this.startX = startX;
        this.startY = startY;
        this.width = width;
        this.height = height;
        this.scene = scene;
        this.image = image;
        this.rayTracer = rayTracer;
    }

    @Override
    public void run() {
        int endY = startY + height;
        int endX = startX + width;
        for (int v = startY; v < endY; v++) {
            for (int u = startX; u < endX; u++) {
                image.setPixel(u, v, rayTracer.renderPixel(scene, u, v));
            }

        }
    }

}
