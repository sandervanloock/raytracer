package be.sandervl.raytracer.business.objects.renderables;

import be.sandervl.raytracer.business.geo.Ray;
import be.sandervl.raytracer.business.math.MathUtils;
import be.sandervl.raytracer.business.math.Vector3D;
import be.sandervl.raytracer.business.scene.Color;

public class Sphere extends Renderable {

    private Vector3D origin;
    private float radius;

    public Sphere(Vector3D origin, float radius, Material material, Color color) {
        this.origin = origin;
        this.radius = radius;
        this.material = material;
        this.color = color;
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
        ray.setIntersectionPoint(ray.getOrigin().add(ray.getDirection().multipy(t)));
        return true;
    }

    @Override
    public Vector3D getSurfaceNorm(Vector3D point) {
        return point.minus(origin);
    }

    @Override
    public Vector3D getPointNorm(Vector3D point) {
        return getSurfaceNorm(point);
    }

    @Override
    public Vector3D getTextureNorm(Vector3D point) {
        return null;
    }

    @Override
    public Color getColor(Vector3D point) {
        Color result = new Color(0,0,0);
        if(color !=null){
            result = result.add(color);
        } else if(material.getTexture()!= null){
            float r = point.norm();
            float theta = (float) Math.acos(point.getZ() / r);
            float omega = (float) Math.atan(point.getY() / point.getX());
            if (omega < 0) {
                omega += Math.PI;
            }
            float u = (float) (omega/(2*Math.PI));
            float v = (float) (theta/(Math.PI));

            Vector3D vt = new Vector3D(v,u,0);
            result = result.add(material.getTexture().getPoint(vt));
        }
        return result;
    }
}
