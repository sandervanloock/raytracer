package be.sandervl.raytracer.business.objects.renderables;

import be.sandervl.raytracer.business.scene.Color;

public class Material {
    private float kd; //diffuse reflection coefficient
    private float ka; //ambient reflection coefficient
    private float ks; //specular reflection coefficient
    private float ns; //specular exponent
    private float r; //reflective coefficient
    private Texture texture;


    public Material(float kd, float ka, float ks, float ns, float r, Texture texture) {
        this.kd = kd;
        this.ka = ka;
        this.ks = ks;
        this.ns = ns;
        this.r = r;
        this.texture = texture;
    }

    public Material() {

    }

    public float getKd() {
        return kd;
    }

    public float getKa() {
        return ka;
    }

    public float getKs() {
        return ks;
    }

    public float getNs() {
        return ns;
    }

    public void setKd(float kd) {
        this.kd = kd;
    }

    public void setKa(float ka) {
        this.ka = ka;
    }

    public void setKs(float ks) {
        this.ks = ks;
    }

    public void setNs(float ns) {
        this.ns = ns;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
