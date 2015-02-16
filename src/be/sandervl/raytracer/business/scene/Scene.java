package be.sandervl.raytracer.business.scene;

import be.sandervl.raytracer.business.math.Vector3D;
import be.sandervl.raytracer.business.objects.lights.Light;
import be.sandervl.raytracer.business.objects.lights.PointLight;
import be.sandervl.raytracer.business.objects.renderables.Renderable;
import be.sandervl.raytracer.business.objects.renderables.Sphere;
import be.sandervl.raytracer.business.objects.renderables.Triangle;

import java.util.*;

public class Scene {
    Set<Renderable> objects;
    Set<Light> lights;
    private final Color background;

    public Scene(Set<Renderable> objects, Set<Light> lights) {
        this.objects = objects;
        this.lights = lights;
        background = new Color(0.1f, 0.1f, 0.1f);
    }

    public static Scene getDefaultScene() {
        Set<Renderable> objects = new HashSet<Renderable>();

//        int dim = 2;
//        float sphereHeight = 0.4f;
//        for (int i = -dim; i <= dim; i++) {
//            for (int j = 0; j <= 2 * dim; j++) {
//                for (int k = -dim; k <= dim; k++) {
//                    objects.add(new Sphere(new Vector3D(i,j+sphereHeight/2,k), sphereHeight, new Color((float)(i+ dim)/(2*dim),(float)(j)/(2*dim),(float)(k+ dim)/(2*dim))));
//                }
//            }
//        }

        int dimSquare = 10;
        Vector3D a = new Vector3D(0, 0, 0);
        Vector3D b = new Vector3D(dimSquare, 0, 0);
        Vector3D c = new Vector3D(0, 0, dimSquare);
        Vector3D d = new Vector3D(-dimSquare, 0, 0);
        Vector3D e = new Vector3D(0, 0, -dimSquare);
        objects.add(new Triangle(a, b, c, b.minus(a).cross(a.minus(c)), new Color(1, 1, 1)));
        objects.add(new Triangle(a, c, d, c.minus(a).cross(a.minus(d)), new Color(1, 1, 1)));
        objects.add(new Triangle(a, d, e, a.minus(e).cross(a.minus(d)), new Color(1, 1, 1)));
        objects.add(new Triangle(a, e, b, e.minus(a).cross(a.minus(b)), new Color(1, 1, 1)));

        Set<Light> lights = new HashSet<Light>();
        lights.add(new PointLight(new Vector3D(0, 20, 20), 0.9f));
        return new Scene(objects, lights);
    }


    public static Scene getEmptyScene() {
        Set<Renderable> objects = new HashSet<Renderable>();

        Set<Light> lights = new HashSet<Light>();
        lights.add(new PointLight(new Vector3D(0, 20, 20), 0.9f));
        return new Scene(objects, lights);
    }


    public Set<Renderable> getObjects() {
        return objects;
    }

    public Set<Light> getLights() {
        return Collections.unmodifiableSet(lights);
    }

    public Color getBackground() {
        return background;
    }
}
