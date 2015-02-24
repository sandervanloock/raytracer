package be.sandervl.raytracer.business.objects.renderables;

import be.sandervl.raytracer.business.scene.Color;

public class Material {
    private float kd; //diffuse reflection coefficient
    private float ka; //ambient reflection coefficient
    private float ks; //specular reflection coefficient
    private int kn; //specular exponent
    private Color color;

    public Material(float kd, float ka, float ks, int kn, Color color) {
        this.kd = kd;
        this.ka = ka;
        this.ks = ks;
        this.kn = kn;
        this.color = color;
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

    public int getKn() {
        return kn;
    }

    public Color getColor() {
        return color;
    }
}
