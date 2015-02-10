package be.sandervl.raytracer.services;

import be.sandervl.raytracer.business.scene.Camera;
import be.sandervl.raytracer.business.scene.Color;
import be.sandervl.raytracer.business.scene.Image;
import be.sandervl.raytracer.business.scene.Scene;

public interface RayTracerService {

    public Image traceScene(Scene scene, Camera camera, int width, int height);

    public Color tracePixel(Scene scene, Camera camera, Image image, int i, int j);

}
