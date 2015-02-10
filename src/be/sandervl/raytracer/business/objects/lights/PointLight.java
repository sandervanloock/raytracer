package be.sandervl.raytracer.business.objects.lights;

import be.sandervl.raytracer.business.math.Vector3D;

public class PointLight implements Light {

    private Vector3D position;
    private float intensity;

    public PointLight(Vector3D position, float intensity) {
        this.position = position;
        this.intensity = intensity;
    }

    @Override
    public Vector3D getPosition() {
        return position;
    }

    @Override
    public float getIntensity() {
        return intensity;
    }
}
