package be.sandervl.raytracer.business.objects.renderables;

import be.sandervl.raytracer.business.scene.Color;

public class Material {
    private float kd; //diffuse reflection coefficient
    private float ka; //ambient reflection coefficient
    private float ks; //specular reflection coefficient
    private float ns; //specular exponent
    private Color color;

    public Material(float kd, float ka, float ks, int ns, Color color) {
        this.kd = kd;
        this.ka = ka;
        this.ks = ks;
        this.ns = ns;
        this.color = color;
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

    public Color getColor() {
        return color;
    }
}
