package be.sandervl.raytracer.business.objects.renderables;

import be.sandervl.raytracer.business.geo.Ray;
import be.sandervl.raytracer.business.math.Vector3D;
import be.sandervl.raytracer.business.objects.lights.Light;
import be.sandervl.raytracer.business.scene.Color;
import be.sandervl.raytracer.business.scene.Scene;

public abstract class Renderable {

    protected Color color;

    public abstract boolean intersect(Ray ray);

    public Color shade(Scene scene, Vector3D point) {
        //ambient light
        float r = (float) (0.2 * this.color.getR());
        float g = (float) (0.2 * this.color.getG());
        float b = (float) (0.2 * this.color.getB());
        Color result = new Color(r, g, b);

        for (Light light : scene.getLights()) {
            Vector3D l = light.getPosition().minus(point);
            l.normalize();
            Vector3D offset = l.multipy(0.001f);
            Ray shadowRay = new Ray(point.add(offset), l);
            if (!shadowRay.intersect(scene)) {
                Vector3D n = getNorm(point);
                n.normalize();
                float v = l.dot(n) * light.getIntensity();
                r = result.getR() + v * this.color.getR();
                g = result.getG() + v * this.color.getG();
                b = result.getB() + v * this.color.getB();
                result = new Color(r, g, b);
            }
        }
        return result;
    }

    public abstract Vector3D getNorm(Vector3D point);
}
