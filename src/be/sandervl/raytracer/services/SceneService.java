package be.sandervl.raytracer.services;

import be.sandervl.raytracer.business.math.Vector3D;
import be.sandervl.raytracer.business.objects.Model;
import be.sandervl.raytracer.business.objects.lights.Light;
import be.sandervl.raytracer.business.objects.renderables.Material;
import be.sandervl.raytracer.business.scene.Color;
import be.sandervl.raytracer.business.scene.Scene;

/**
 * Created by sander on 10/02/2015.
 */
public interface SceneService {

    void addPaneToScene(Vector3D orgin, float size, Scene scene);

    void addModelToScene(Model model, Scene scene);

    void addSphereToScene(Vector3D origin, float radius, Material material, Color color, Scene scene);

    void addSphereQuebeToScene(int dim, float radius, boolean colorized, Scene scene);

    void addLightToScene(Light light, Scene scene);
}
