package be.sandervl.raytracer.business.objects.lights;

import be.sandervl.raytracer.business.math.Vector3D;

public interface Light {
    Vector3D getPosition();

    float getIntensity();
}
