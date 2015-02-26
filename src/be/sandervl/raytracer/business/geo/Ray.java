package be.sandervl.raytracer.business.geo;

import be.sandervl.raytracer.business.math.Vector3D;
import be.sandervl.raytracer.business.objects.lights.Light;
import be.sandervl.raytracer.business.objects.renderables.Renderable;
import be.sandervl.raytracer.business.scene.Camera;
import be.sandervl.raytracer.business.scene.Color;
import be.sandervl.raytracer.business.scene.Scene;

public class Ray {
    public final static float MAX_T = Float.MAX_VALUE;
    private Vector3D origin, direction;
    private Vector3D intersectionPoint;

    private float t;

    public Ray(Vector3D origin, Vector3D direction) {
        this.origin = origin;
        this.direction = direction;
    }

    public boolean intersect(Renderable object) {
        return object.intersect(this);
    }

    public boolean intersect(Scene scene) {
        this.t = Ray.MAX_T;
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

    public Vector3D getIntersectionPoint() {
        return intersectionPoint;
    }

    public void setIntersectionPoint(Vector3D intersectionPoint) {
        this.intersectionPoint = intersectionPoint;
    }

    public Color trace(Scene scene, Camera camera) {
        Renderable closestHit = null;
        this.t = Ray.MAX_T;
        for (Renderable object : scene.getObjects()) {
            if (this.intersect(object)) {
                closestHit = object;
            }
        }
        if (closestHit != null) {
            return this.shade(scene, camera, closestHit);
        }
        return scene.getBackground();
    }

    private Color shade(Scene scene, Camera camera, Renderable renderable) {
        Color result = renderable.getColor(this.getIntersectionPoint());

        Vector3D point = this.origin.add(this.direction.multipy(t));

        //ambient light
        result = result.multiply(renderable.getMaterial().getKa());

        for (Light light : scene.getLights()) {
            Vector3D l = light.getPosition().minus(point);
            l.normalize();
            Vector3D offset = l.multipy(0.1f);
            Ray shadowRay = new Ray(point.add(offset), l);
            if (!shadowRay.intersect(scene)) {
                Vector3D n = renderable.getPointNorm(point);
                n.normalize();
                float lambertian = l.dot(n) * light.getIntensity();

                Vector3D viewDir = camera.getEye().minus(point);
                viewDir.normalize();
                Vector3D h = l.add(viewDir).divide(2);
                h.normalize();
                float nDotH = n.dot(h);
                float specular = renderable.getMaterial().getKs() * (float) Math.pow(nDotH, renderable.getMaterial().getNs());

                result = result.add(result.multiply(lambertian)).add(result.multiply(specular));
                float reflectiveCoef = renderable.getMaterial().getR();
                if(reflectiveCoef >0){
                    float c1 = -1*n.dot(this.direction);
                    Vector3D r = this.direction.add(n.multipy(2*c1));
                    Ray reflectiveRay = new Ray(point,r);
                    Color reflectiveColor = reflectiveRay.trace(scene,camera);
                    result = reflectiveColor.multiply(reflectiveCoef).add(result.multiply(1-reflectiveCoef));
                }
            }
        }
        return result;
    }
}
