package be.sandervl.raytracer.business.objects.renderables;

import be.sandervl.raytracer.business.geo.Ray;
import be.sandervl.raytracer.business.math.Vector3D;
import be.sandervl.raytracer.business.scene.Color;

public class Sphere extends Renderable {

    private Vector3D origin;
    private float radius;

    public Sphere(Vector3D origin, float radius, Color color) {
        this.origin = origin;
        this.radius = radius;
        this.color=color;
    }

    public boolean intersect(Ray ray) {
        float dx = origin.getX() - ray.getOrigin().getX();
        float dy = origin.getY() - ray.getOrigin().getY();
        float dz = origin.getZ() - ray.getOrigin().getZ();
        float v = ray.getDirection().dot(new Vector3D(dx, dy, dz));

        // Do the following quick check to see if there is even a chance
        // that an intersection here might be closer than a previous one
        if (v - radius > ray.getT())
            return false;

        // Test if the ray actually intersects the sphere
        float t = radius * radius + v * v - dx * dx - dy * dy - dz * dz;
        if (t < 0)   {
            return false;
        }

        // Test if the intersection is in the positive
        // ray direction and it is the closest so far
        t = v - ((float) Math.sqrt(t));
        if ((t > ray.getT()) || (t < 0)) {
            return false;
        }

        ray.setT(t);
        return true;
    }

    @Override
    public Vector3D getNorm(Vector3D point) {
        return point.minus(origin);
    }
}
