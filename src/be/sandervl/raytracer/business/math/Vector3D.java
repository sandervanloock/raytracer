package be.sandervl.raytracer.business.math;

public class Vector3D {
    float x, y, z;

    public Vector3D(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void scalar(float a) {
        this.x *= a;
        this.y *= a;
        this.z *= a;
    }

    public float dot(Vector3D other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    public Vector3D cross(Vector3D other) {
        return new Vector3D(this.y * other.z - this.z * other.y, this.z * other.x - this.x * other.z, this.x * other.y - this.y * other.x);
    }

    public void normalize() {
        float norm = norm();
        if (norm != 0) {
            this.x /= norm;
            this.y /= norm;
            this.z /= norm;
        }
    }

    public float norm() {
        return (float) Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    public Vector3D minus(Vector3D other) {
        return new Vector3D(this.x-other.x, this.y-other.y,this.z-other.z);
    }

    public Vector3D add(Vector3D other) {
        return new Vector3D(this.x+other.x, this.y+other.y,this.z+other.z);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public Vector3D multipy(float t) {
        return new Vector3D(this.x*t,this.y*t,this.z*t);
    }

    public String toString(){
        return String.format("[%s,%s,%s]",x,y,z);
    }
}
