package be.sandervl.raytracer;

import be.sandervl.raytracer.business.geo.Ray;
import be.sandervl.raytracer.business.math.Vector3D;
import be.sandervl.raytracer.business.scene.Camera;
import be.sandervl.raytracer.business.scene.Color;
import be.sandervl.raytracer.business.scene.Scene;

public class RayTracer {
    private Camera camera;
    private Scene scene;
    private Vector3D Du, Dv, Vp;

    public RayTracer(Scene scene, Camera camera, int width, int height) {
        this.scene = scene;
        this.camera = camera;
        Du = camera.getLook().cross(camera.getUp());
        Du.normalize();
        Dv = camera.getLook().cross(Du);
        Dv.normalize();
        float fl = (float) (width / (2 * Math.tan((0.5 * camera.getFov()) * Math.PI / 180)));
        Vp = camera.getLook();
        Vp.normalize();
        Vp.setX(Vp.getX() * fl - 0.5f * (width * Du.getX() + height * Dv.getX()));
        Vp.setY(Vp.getY() * fl - 0.5f * (width * Du.getY() + height * Dv.getY()));
        Vp.setZ(Vp.getZ() * fl - 0.5f * (width * Du.getZ() + height * Dv.getZ()));
    }

    public Color renderPixel(Scene scene, int i, int j) {
        Vector3D direction =
                new Vector3D(
                        i * Du.getX() + j * Dv.getX() + Vp.getX(),
                        i * Du.getY() + j * Dv.getY() + Vp.getY(),
                        i * Du.getZ() + j * Dv.getZ() + Vp.getZ());
        direction.normalize();
        Ray ray = new Ray(camera.getEye(), direction);
        return ray.trace(scene, camera);
    }
}
