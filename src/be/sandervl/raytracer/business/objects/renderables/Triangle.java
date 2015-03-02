package be.sandervl.raytracer.business.objects.renderables;

import be.sandervl.raytracer.business.geo.Ray;
import be.sandervl.raytracer.business.math.Vector3D;
import be.sandervl.raytracer.business.scene.Color;

public class Triangle extends Renderable {

    private final Vector3D a, b, c;
    private Vector3D bMinusA, cMinusA, cMinusB, aMinusC, norm, na, nb, nc, ta, tb, tc;
    private float bMinusACrossCMinusA;

    public Triangle(Vector3D a, Vector3D b, Vector3D c, Material material, Color color) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.material = material;
        this.color = color;
        calculateNormals();
        this.na = norm;
        this.nb = norm;
        this.nc = norm;
    }

    public Triangle(Vector3D a, Vector3D b, Vector3D c, Vector3D na, Vector3D nb, Vector3D nc, Vector3D ta, Vector3D tb, Vector3D tc, Material material, Color color) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.material = material;
        this.color = color;
        calculateNormals();
        this.ta = ta;
        this.tb = tb;
        this.tc = tc;
        this.na = na;
        this.nb = nb;
        this.nc = nc;
    }

    public Triangle(Vector3D a, Vector3D b, Vector3D c, Vector3D na, Vector3D nb, Vector3D nc, Material material, Color color) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.na = na;
        this.nb = nb;
        this.nc = nc;
        this.material = material;
        this.color = color;
        calculateNormals();
    }

    private void calculateNormals() {
        this.bMinusA = b.minus(a);
        this.cMinusA = c.minus(a);
        this.cMinusB = c.minus(b);
        this.aMinusC = a.minus(c);
        this.norm = bMinusA.cross(cMinusA);
        this.norm.normalize();
        bMinusACrossCMinusA = bMinusA.cross(cMinusA).dot(norm);
    }

    @Override
    public boolean intersect(Ray ray) {
        float normDotDirection = norm.dot(ray.getDirection());
        if (normDotDirection == 0) {
            return false;
        }

        float t = norm.dot(this.a.minus(ray.getOrigin())) / normDotDirection;
        Vector3D intersectionPoint = ray.getOrigin().add(ray.getDirection().multipy(t));
        float alpha = (cMinusB.cross(intersectionPoint.minus(b))).dot(norm) / bMinusACrossCMinusA;
        if (alpha < 0) {
            return false;
        } else {
            float beta = aMinusC.cross(intersectionPoint.minus(c)).dot(norm) / bMinusACrossCMinusA;
            float gamma = 1- alpha - beta;
            if (beta < 0 || gamma > 1 || gamma < 0 || t > ray.getT() || t < 0) {
                return false;
            }
            ray.setT(t);
            ray.setIntersectionPoint(new Vector3D(alpha, beta, gamma));
            return true;
        }
    }

    @Override
    public Vector3D getSurfaceNorm(Vector3D point) {
        return norm;
    }

    @Override
    public Vector3D getPointNorm(Vector3D point) {
        return na.multipy(point.getX()).add(nb.multipy(point.getY())).add(nc.multipy(point.getZ()));
    }

    @Override
    public Vector3D getTextureNorm(Vector3D point) {
        float alpha = (cMinusB.cross(point.minus(b))).dot(norm) / bMinusACrossCMinusA;
        float beta = aMinusC.cross(point.minus(c)).dot(norm) / bMinusACrossCMinusA;
        float gamma = 1- alpha - beta;
        return ta.multipy(alpha).add(tb.multipy(beta)).add(tc.multipy(gamma));
    }

    @Override
    public Color getColor(Vector3D point) {
        Color result = new Color(0,0,0);
        if(color !=null){
            result = result.add(color);
        } else if(material.getTexture()!= null){
            Vector3D vt = ta.multipy(point.getX()).add(tb.multipy(point.getY())).add(tc.multipy(point.getZ()));
            result = result.add(material.getTexture().getPoint(vt));
        }
        return result;
    }
}
