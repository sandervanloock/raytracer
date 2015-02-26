package be.sandervl.raytracer.services;

import be.sandervl.raytracer.business.math.Vector3D;
import be.sandervl.raytracer.business.objects.Model;
import be.sandervl.raytracer.business.objects.lights.Light;
import be.sandervl.raytracer.business.objects.renderables.*;
import be.sandervl.raytracer.business.scene.Color;
import be.sandervl.raytracer.business.scene.Scene;
import be.sandervl.raytracer.services.reader.ModelReaderService;
import be.sandervl.raytracer.services.reader.ModelReaderServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Set;

/**
 * Created by sander on 10/02/2015.
 */
public class SceneServiceImpl implements SceneService {

    private ModelReaderService modelReaderService;

    private static final Logger LOG = LoggerFactory.getLogger(SceneService.class);

    public SceneServiceImpl() {
        modelReaderService = new ModelReaderServiceImpl();
    }

    @Override
    public void addPaneToScene(Vector3D origin, float size, Scene scene) {
        Vector3D b = new Vector3D(size, 0, 0).add(origin);
        Vector3D c = new Vector3D(0, 0, size).add(origin);
        Vector3D d = new Vector3D(-size, 0, 0).add(origin);
        Vector3D e = new Vector3D(0, 0, -size).add(origin);
        Material material = new Material(0.5f,0.2f,0.5f,8,0,null);
        scene.addRenderable(new Triangle(c, b, origin, material,new Color(0.7f,0.7f,0.7f)));
        scene.addRenderable(new Triangle(d, c, origin, material,new Color(0.7f,0.7f,0.7f)));
        scene.addRenderable(new Triangle(e, d, origin, material,new Color(0.7f,0.7f,0.7f)));
        scene.addRenderable(new Triangle(b, e, origin, material,new Color(0.7f,0.7f,0.7f)));
        LOG.debug("added PANE in origin {} and size {} to scene", origin, size);
    }

    @Override
    public void addModelToScene(Model model, Scene scene) {
        File file = new File(model.getFileName());
        Set<Renderable> triangles = modelReaderService.readModel(file);
        scene.addRenderables(triangles);
        LOG.debug("added MODEL {} to scene",model);
    }

    @Override
    public void addSphereToScene(Vector3D origin, float radius, Material material, Color color, Scene scene) {
        Sphere sphere = new Sphere(origin,radius,material,color);
        scene.addRenderable(sphere);
    }

    @Override
    public void addSphereQuebeToScene(int dim, float radius, boolean colorized, Scene scene) {
        for (int i = -dim; i <= dim; i++) {
            for (int j = 0; j <= 2 * dim; j++) {
                for (int k = -dim; k <= dim; k++) {
                    Color color = new Color(1, 1, 1);
                    if (colorized) {
                        color = new Color((float) (i + dim) / (2 * dim), (float) (j) / (2 * dim), (float) (k + dim) / (2 * dim));
                    }
                    Material material = new Material(0.5f,0.5f,0.5f,16,0,null);
                    scene.addRenderable(new Sphere(new Vector3D(i, j + radius / 2, k), radius, material,color));
                }
            }
        }
        LOG.debug("added SPHERE QUEBE with dimension {} and radii {} to scene",dim,radius);
    }


    @Override
    public void addLightToScene(Light light, Scene scene) {
        scene.addLight(light);
    }


}
