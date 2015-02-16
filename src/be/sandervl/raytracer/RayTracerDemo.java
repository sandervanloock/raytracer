package be.sandervl.raytracer;

import be.sandervl.raytracer.business.objects.renderables.Triangle;
import be.sandervl.raytracer.business.scene.Camera;
import be.sandervl.raytracer.business.scene.Color;
import be.sandervl.raytracer.business.scene.Image;
import be.sandervl.raytracer.business.scene.Scene;
import be.sandervl.raytracer.services.RayTracerService;
import be.sandervl.raytracer.services.RayTracerServiceImpl;
import be.sandervl.raytracer.services.reader.ModelReaderService;
import be.sandervl.raytracer.services.reader.ModelReaderServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.List;

public class RayTracerDemo {

    private RayTracerService rayTracerService;
    private ModelReaderService modelReaderService;

    public RayTracerDemo() {

        this.rayTracerService = new RayTracerServiceImpl();
        this.modelReaderService= new ModelReaderServiceImpl();
    }

    public static void main(String s[]) {
        Camera camera = Camera.getDefaultCamera();
        Scene scene = Scene.getDefaultScene();
        int width = 512;
        int height = 512;

        RayTracerDemo demo = new RayTracerDemo();

        List<Triangle> model = demo.modelReaderService.readModel(new File("data/torus.obj"));
        scene.getObjects().addAll(model);

        Image result = demo.rayTracerService.traceScene(scene, camera, width, height);

        JFrame frame = new JFrame("Raytracer Demo");
        // Add a window listner for close button
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        RenderedImage image = new RenderedImage(result.getPixels());
        frame.add(image);
        Dimension preferredSize = new Dimension(result.getWidth(), result.getHeight());
        frame.setSize(preferredSize);
        frame.setVisible(true);
        ClickPixelMouseListener l = new ClickPixelMouseListener(scene, camera, result);
        frame.addMouseListener(l);
    }

    private static class RenderedImage extends JComponent {
        private Color[][] pixels;

        public RenderedImage(Color[][] pixels) {
            this.pixels = pixels;
        }

        public void paintComponent(Graphics g) {
            for (int y = 0; y < pixels[0].length; y++) {
                for (int x = 0; x < pixels.length; x++) {
                    Rectangle box = new Rectangle(x, y, 1, 1);
                    Color color = pixels[x][y];
                    g.setColor(new java.awt.Color(color.getR(),color.getG(),color.getB()));
                    g.drawLine(x, y, x, y);
                }
            }
        }
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
