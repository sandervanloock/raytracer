package be.sandervl.raytracer.services;

import be.sandervl.raytracer.business.objects.renderables.Renderable;

import java.util.List;

/**
 * Created by sander on 10/02/2015.
 */
public interface SceneService {

    void addRenderables(List<Renderable> renderables);

}
