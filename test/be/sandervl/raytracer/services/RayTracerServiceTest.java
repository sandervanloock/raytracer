package be.sandervl.raytracer.services;

import be.sandervl.raytracer.business.math.Vector3D;
import be.sandervl.raytracer.business.scene.Camera;
import be.sandervl.raytracer.business.scene.Color;
import be.sandervl.raytracer.business.scene.Image;
import be.sandervl.raytracer.business.scene.Scene;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class RayTracerServiceTest {

    private RayTracerService rayTracerService;

    @Before
    public void setupTest() {
        rayTracerService = new RayTracerServiceImpl();
    }

    @Test
    public void testTraceScene() {
        Camera camera = new Camera(new Vector3D(10,10,10),new Vector3D(0,0,0),new Vector3D(0,1,0));
        Scene scene = new Scene();
        int width = 512;
        int height = 512;

        Image result = rayTracerService.traceScene(scene, camera, width, height);

        Assert.assertNotNull(result);
        Assert.assertEquals(512, result.getWidth());
        Assert.assertEquals(512, result.getHeight());

        boolean isRedPresent = false;
        Color[][] pixels = result.getPixels();
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                Color pixel = pixels[i][j];
                Assert.assertTrue(pixel.equals(new Color(0,0,0)) || pixel.equals(new Color(1,0,0)));
                if (pixel.equals(new Color(1,0,0))) {
                    isRedPresent = true;
                }
            }
        }
        Assert.assertTrue(isRedPresent);
    }
}
