package be.sandervl.raytracer.services;

import be.sandervl.raytracer.business.objects.renderables.Renderable;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by sander on 10/02/2015.
 */
public class SceneServiceTest {

    private SceneService sceneService;

    @Test
    public void test(){
        ArrayList<Renderable> renderables = new ArrayList<Renderable>();
        sceneService.addRenderables(renderables);


    }
}
