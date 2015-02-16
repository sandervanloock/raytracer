package be.sandervl.raytracer.business.scene;

import be.sandervl.raytracer.business.objects.lights.Light;
import be.sandervl.raytracer.business.objects.renderables.Renderable;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Scene {
    Set<Renderable> objects = new HashSet<Renderable>();
    Set<Light> lights = new HashSet<Light>();
    private final Color background = new Color(0.1f, 0.1f, 0.1f);

    public Scene() {
    }

    public Set<Renderable> getObjects() {
        return Collections.unmodifiableSet(objects);
    }

    public Set<Light> getLights() {
        return Collections.unmodifiableSet(lights);
    }

    public Color getBackground() {
        return background;
    }

    public void addRenderable(Renderable renderable) {
        this.objects.add(renderable);
    }

    public void addRenderables(Set<Renderable> renderables) {
        this.objects.addAll(renderables);
    }

    public void addLight(Light light) {
        this.lights.add(light);
    }
}
