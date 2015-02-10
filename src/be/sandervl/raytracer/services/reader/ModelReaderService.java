package be.sandervl.raytracer.services.reader;

import be.sandervl.raytracer.business.objects.renderables.Triangle;

import java.io.File;
import java.util.List;

/**
 * Created by sander on 10/02/2015.
 */
public interface ModelReaderService {

    List<Triangle> readModel(File file);
}
