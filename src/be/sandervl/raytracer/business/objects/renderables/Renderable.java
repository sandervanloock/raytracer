package be.sandervl.raytracer.business.objects.renderables;

import be.sandervl.raytracer.business.geo.Ray;
import be.sandervl.raytracer.business.math.Vector3D;
import be.sandervl.raytracer.business.objects.lights.Light;
import be.sandervl.raytracer.business.scene.Camera;
import be.sandervl.raytracer.business.scene.Color;
import be.sandervl.raytracer.business.scene.Scene;

public abstract class Renderable {

    protected Material material;

    public abstract boolean intersect(Ray ray);

    public Color shade(Scene scene, Camera camera, Vector3D point) {
        Color color = material.getColor();

        //ambient light
        float r = material.getKa() * color.getR();
        float g = material.getKa() * color.getG();
        float b = material.getKa() * color.getB();
        Color result = new Color(r, g, b);

        for (Light light : scene.getLights()) {
            Vector3D l = light.getPosition().minus(point);
            l.normalize();
            Vector3D offset = l.multipy(0.001f);
            Ray shadowRay = new Ray(point.add(offset), l);
            if (!shadowRay.intersect(scene)) {
                Vector3D n = getPointNorm(point);
                n.normalize();
                float lambertian = l.dot(n) * light.getIntensity();
//                float distance = l.norm();

                Vector3D viewDir = camera.getEye().minus(point);
                viewDir.normalize();
                Vector3D h = l.add(viewDir).divide(2);
                h.normalize();
                float nDotH = n.dot(h);
                float specular = material.getKs()*(float)Math.pow(nDotH, material.getKn());

//                distance *= distance;
                r = result.getR() + lambertian * color.getR() + specular * color.getR();
                g = result.getG() + lambertian * color.getG() + specular * color.getG();
                b = result.getB() + lambertian * color.getB() + specular * color.getB();
                result = new Color(r, g, b);
            }
        }
        return result;
    }

    public abstract Vector3D getSurfaceNorm(Vector3D point);

    public abstract Vector3D getPointNorm(Vector3D point);
}
