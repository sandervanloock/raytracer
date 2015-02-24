package be.sandervl.raytracer;

import be.sandervl.raytracer.business.math.Vector3D;
import be.sandervl.raytracer.business.objects.Model;
import be.sandervl.raytracer.business.objects.lights.PointLight;
import be.sandervl.raytracer.business.scene.*;
import be.sandervl.raytracer.business.scene.Color;
import be.sandervl.raytracer.business.scene.Image;
import be.sandervl.raytracer.services.RayTracerService;
import be.sandervl.raytracer.services.RayTracerServiceImpl;
import be.sandervl.raytracer.services.SceneService;
import be.sandervl.raytracer.services.SceneServiceImpl;
import be.sandervl.raytracer.services.reader.ModelReaderService;
import be.sandervl.raytracer.services.reader.ModelReaderServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RayTracerDemo implements Runnable {

    private RayTracerService rayTracerService;
    private ModelReaderService modelReaderService;
    private SceneService sceneService;

    private static final Logger LOG = LoggerFactory.getLogger(RayTracerDemo.class);

    public RayTracerDemo() {
        this.rayTracerService = new RayTracerServiceImpl();
        this.modelReaderService = new ModelReaderServiceImpl();
        this.sceneService = new SceneServiceImpl();
    }

    @Override
    public void run() {
        LOG.debug("Ray Tracer demo started");
        long start = System.currentTimeMillis();

        Vector3D eye = new Vector3D(5,5,5);
        Vector3D lookat = new Vector3D(0, 0, 0);
        Vector3D up = new Vector3D(0, 1, 0);
        Camera camera = new Camera(eye, lookat, up);
        Scene scene = new Scene();

        int width = 512;
        int height = 512;

        PointLight light = new PointLight(new Vector3D(500, 500, 500), 0.5f);
        sceneService.addLightToScene(light, scene);
        light = new PointLight(new Vector3D(0, 250, 100), 0.1f);
        sceneService.addLightToScene(light, scene);
        sceneService.addPaneToScene(new Vector3D(0, 0, 0), 5, scene);
        sceneService.addModelToScene(Model.THEAPOT, scene);

        LOG.debug("Ray Tracer demo counted {} triangles", scene.getObjects().size());


        JFrame frame = new JFrame("Raytracer Demo");
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        Color[][] pixels = new Color[width][height];
        for(int i=0;i<pixels.length;i++){
            for(int j=0;j<pixels[0].length;j++){
                pixels[i][j] = new Color(0,0,0);
            }
        }
        RenderedImage image = new RenderedImage(pixels);
        frame.add(image);
        Dimension preferredSize = new Dimension(width, height);
        frame.setSize(preferredSize);
        frame.setVisible(true);

        rayTracerService.traceScene(scene, camera, width, height, image);

        LOG.debug("Ray Tracer demo ended after {} seconds", (System.currentTimeMillis() - start) / 1000);
    }

    public static void main(String s[]) {
        RayTracerDemo demo = new RayTracerDemo();
        demo.run();
    }



    private static class ClickPixelMouseListener implements MouseListener {


        private Scene scene;
        private Camera camera;
        private Image image;
        private RayTracerService rayTracerService;

        private ClickPixelMouseListener(Scene scene, Camera camera, Image image) {
            this.scene = scene;
            this.camera = camera;
            this.image = image;
            this.rayTracerService = new RayTracerServiceImpl();
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            rayTracerService.tracePixel(scene, camera, image, e.getX(), e.getY());
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
