package be.sandervl.raytracer.business.scene;

import be.sandervl.raytracer.business.math.MathUtils;

public class Color{

    private float r,g,b;

    /**
     *
     * @param r
     * @param g
     * @param b
     */
    public Color(float r, float g, float b) {
        this.r = MathUtils.round(0, 1, r);
        this.g = MathUtils.round(0, 1, g);
        this.b = MathUtils.round(0, 1, b);
    }

    public float getR() {
        return r;
    }

    public float getG() {
        return g;
    }

    public float getB() {
        return b;
    }

}
