package be.sandervl.raytracer.business.objects.renderables;

import be.sandervl.raytracer.business.geo.Ray;
import be.sandervl.raytracer.business.math.Vector3D;
import be.sandervl.raytracer.business.objects.lights.Light;
import be.sandervl.raytracer.business.scene.Camera;
import be.sandervl.raytracer.business.scene.Color;
import be.sandervl.raytracer.business.scene.Scene;

public abstract class Renderable {

    protected Material material;
    protected Color color;

    public abstract boolean intersect(Ray ray);

    public abstract Vector3D getSurfaceNorm(Vector3D point);

    public abstract Vector3D getPointNorm(Vector3D point);

    public abstract Vector3D getTextureNorm(Vector3D point);

    public abstract Color getColor(Vector3D barycentricCoordinatesHit);

    public Material getMaterial() {
        return material;
    }
}
