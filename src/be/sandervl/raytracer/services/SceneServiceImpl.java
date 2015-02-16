package be.sandervl.raytracer.services;

import be.sandervl.raytracer.business.objects.renderables.Renderable;
import be.sandervl.raytracer.business.scene.Scene;

import java.util.List;

/**
 * Created by sander on 10/02/2015.
 */
public class SceneServiceImpl implements SceneService {

    private Scene scene;

    public SceneServiceImpl(Scene scene) {
        this.scene = scene;
    }

    @Override
    public void addRenderables(List<Renderable> renderables) {
        for(Renderable renderable: renderables){
            scene.getObjects().add(renderable);
        }
    }


}
