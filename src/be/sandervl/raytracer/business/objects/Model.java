package be.sandervl.raytracer.business.objects;

/**
 * Created by sander on 16/02/2015.
 */
public enum Model {

    QUBE ("data/qube.obj"),
    CILINDER("data/cilinder.obj"),
    TORUS("data/torus.obj");

    private String fileName;

    Model(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
