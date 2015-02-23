package be.sandervl.raytracer.services;


import be.sandervl.raytracer.RayTracer;
import be.sandervl.raytracer.RayTracerJob;
import be.sandervl.raytracer.business.scene.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RayTracerServiceImpl implements RayTracerService {

    private static final int THREAD_SIZE = 16;

    @Override
    public Color tracePixel(Scene scene, Camera camera, Image image, int i, int j) {
        RayTracer rayTracer = new RayTracer(scene, camera, image.getWidth(), image.getHeight());
        return rayTracer.renderPixel(scene, i, j);
    }

    @Override
    public Image traceScene(Scene scene, Camera camera, int width, int height) {
        RayTracer rayTracer = new RayTracer(scene, camera, width, height);
        Color[][] pixels = new Color[width][height];
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                pixels[i][j] = rayTracer.renderPixel(scene, i, j);
            }
        }
        Image image = new Image(pixels);
        return image;
    }

    @Override
    public void traceScene(Scene scene, Camera camera, int width, int height, RenderedImage image) {
        RayTracer rayTracer = new RayTracer(scene, camera, width, height);
        Color[][] pixels = new Color[width][height];
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_SIZE);
        int blockWidth = (int) Math.ceil(width / Math.sqrt(THREAD_SIZE));
        int blockHeight = (int) Math.ceil(height / Math.sqrt(THREAD_SIZE));
        for (int j = 0; j < height; j += blockHeight) {
            for (int i = 0; i < width; i += blockWidth) {
                RayTracerJob rayTracerJob = new RayTracerJob(i, j, blockWidth, blockHeight, scene, camera, image, rayTracer);
                executor.execute(rayTracerJob);
            }
        }
        executor.shutdown();
    }

}
