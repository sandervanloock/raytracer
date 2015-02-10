package be.sandervl.raytracer.business.objects.renderables;

import be.sandervl.raytracer.business.geo.Ray;
import be.sandervl.raytracer.business.math.Vector3D;
import be.sandervl.raytracer.business.scene.Color;

public class Triangle extends Renderable {

    private final Vector3D a, b, c, bMinusA, cMinusA, cMinusB, aMinusC, norm;
    private final float bMinusACrossCMinusA,d;

    public Triangle(Vector3D a, Vector3D b, Vector3D c, Color color) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.bMinusA = b.minus(a);
        this.cMinusA = c.minus(a);
        this.cMinusB = c.minus(b);
        this.aMinusC = a.minus(c);
        this.norm = bMinusA.cross(cMinusA);
        this.norm.normalize();
        this.d = norm.dot(a);
        this.color = color;
        bMinusACrossCMinusA = bMinusA.cross(cMinusA).dot(norm);
    }

    public Triangle(Vector3D a, Vector3D b, Vector3D c, Vector3D norm, Color color) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.bMinusA = b.minus(a);
        this.cMinusA = c.minus(a);
        this.cMinusB = c.minus(b);
        this.aMinusC = a.minus(c);
        this.norm = norm;
        this.norm.normalize();
        this.d = norm.dot(a);
        this.color = color;
        bMinusACrossCMinusA = bMinusA.cross(cMinusA).dot(norm);
    }

    @Override
    public boolean intersect(Ray ray) {

        //ray is parallel with triangle plane
        float normDotDirection = norm.dot(ray.getDirection());
        if (normDotDirection == 0) {
            return false;
        }

        float t = d - norm.dot(ray.getOrigin()) / normDotDirection;
        Vector3D intersectionPoint = ray.getOrigin().add(ray.getDirection().multipy(t));

        float alpha = cMinusB.cross(intersectionPoint.minus(b)).dot(norm) / bMinusACrossCMinusA;
        if (alpha < 0) {
            return false;
        } else {
            float beta = aMinusC.cross(intersectionPoint.minus(c)).dot(norm) / bMinusACrossCMinusA;
            if (beta < 0 || alpha + beta > 1 || t > ray.getT() || t < 0) {
                return false;
            }
            ray.setT(t);
            return true;
        }
    }

    @Override
    public Vector3D getNorm(Vector3D point) {
        return norm;
    }
}
