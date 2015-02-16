package be.sandervl.raytracer.business.scene;

import be.sandervl.raytracer.business.math.Vector3D;

public class Camera {
    private final Vector3D eye, lookat, look, up;
    private final int fov;


    public Camera(Vector3D eye, Vector3D lookat, Vector3D up) {
        this.eye = eye;
        this.lookat = lookat;
        this.up = up;
        this.fov = 30;

        look = new Vector3D(lookat.getX() - eye.getX(), lookat.getY() - eye.getY(), lookat.getZ() - eye.getZ());
    }

    public Vector3D getEye() {
        return eye;
    }

    public Vector3D getLook() {
        return look;
    }

    public Vector3D getUp() {
        return up;
    }

    public int getFov() {
        return fov;
    }
}
