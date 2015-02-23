package be.sandervl.raytracer;

import be.sandervl.raytracer.business.scene.Camera;
import be.sandervl.raytracer.business.scene.RenderedImage;
import be.sandervl.raytracer.business.scene.Scene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by sander on 16/02/2015.
 */
public class RayTracerJob implements Runnable {

    private final int startX, startY, width, height;
    private final Scene scene;
    private RayTracer rayTracer;
    private RenderedImage image;

    private static Logger LOG = LoggerFactory.getLogger(RayTracerJob.class);

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
        long start = System.currentTimeMillis();
        LOG.debug("JOB started at {}", start);

        int endY = startY + height;
        int endX = startX + width;
        for (int v = startY; v < endY; v++) {
            for (int u = startX; u < endX; u++) {
                image.setPixel(u, v, rayTracer.renderPixel(scene, u, v));
            }


        }
        long end = System.currentTimeMillis();
        LOG.debug("JOB ended at {}", end);
        LOG.debug("JOB duration took {} ms",  end-start);
    }

}
