package be.sandervl.raytracer.services;


import be.sandervl.raytracer.RayTracer;
import be.sandervl.raytracer.business.scene.Camera;
import be.sandervl.raytracer.business.scene.Color;
import be.sandervl.raytracer.business.scene.Image;
import be.sandervl.raytracer.business.scene.Scene;

public class RayTracerServiceImpl implements RayTracerService {


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

}
