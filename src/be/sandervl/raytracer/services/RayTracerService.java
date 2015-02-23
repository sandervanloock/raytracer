package be.sandervl.raytracer.services;

import be.sandervl.raytracer.business.scene.*;

public interface RayTracerService {

    RenderedImage traceScene(Scene scene, Camera camera, int width, int height);

    Color tracePixel(Scene scene, Camera camera, Image image, int i, int j);

    void traceScene(Scene scene, Camera camera, int width, int height, RenderedImage image);

}
