package be.sandervl.raytracer.business.geo;

import be.sandervl.raytracer.business.math.Vector3D;
import be.sandervl.raytracer.business.objects.renderables.Renderable;
import be.sandervl.raytracer.business.scene.Color;
import be.sandervl.raytracer.business.scene.Scene;

public class Ray {
    public final static float MAX_T = Float.MAX_VALUE;
    private Vector3D origin, direction;

    private float t;

    public Ray(Vector3D origin, Vector3D direction) {
        this.origin = origin;
        this.direction = direction;
    }

    public boolean intersect(Renderable object) {
        return object.intersect(this);
    }

    public boolean intersect(Scene scene) {
        this.setT(Ray.MAX_T);
        for (Renderable object : scene.getObjects()) {
            if (this.intersect(object)) {
                return true;
            }
        }
        return false;
    }

    public Vector3D getOrigin() {
        return origin;
    }

    public Vector3D getDirection() {
        return direction;
    }

    public float getT() {
        return t;
    }

    public void setT(float t) {
        this.t = t;
    }

    public Color trace(Scene scene) {
        Renderable closestHit = null;
        this.setT(Ray.MAX_T);
        for (Renderable object : scene.getObjects()) {
            if (this.intersect(object)) {
                closestHit = object;
            }
        }
        if (closestHit != null) {
            return closestHit.shade(scene, this.getIntersectionPoint());
        }
        return scene.getBackground();
    }

    public Vector3D getIntersectionPoint() {
        return this.origin.add(this.direction.multipy(getT()));
    }
}
