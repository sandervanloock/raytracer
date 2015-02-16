package be.sandervl.raytracer.services.reader;

import be.sandervl.raytracer.business.objects.renderables.Renderable;
import be.sandervl.raytracer.business.objects.renderables.Triangle;

import java.io.File;
import java.util.Set;

/**
 * Created by sander on 10/02/2015.
 */
public interface ModelReaderService {

    Set<Renderable> readModel(File file);
}
