package be.sandervl.raytracer.services.reader;

import be.sandervl.raytracer.business.objects.renderables.Material;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by sander on 24/02/2015.
 */
public class ModelReaderServiceTest {

    private ModelReaderService modelReaderService;

    @Before
    public void setup(){
        modelReaderService = new ModelReaderServiceImpl();
    }

    @Test
    public void testReadOneMaterials(){
        Map<String,Material> actualMaterials = modelReaderService.readMaterials(new File("data/cilinder.mtl"));

        assertNotNull(actualMaterials);
        assertEquals(1,actualMaterials.values().size());

        assertNotNull(actualMaterials.get("None"));
    }

    @Test
    public void testReadManyMaterials(){
        Map<String,Material> actualMaterials = modelReaderService.readMaterials(new File("data/Wall-E LowPoly/Wall-Elowpoly.mtl"));

        assertNotNull(actualMaterials);
        assertEquals(5,actualMaterials.values().size());

        assertNotNull(actualMaterials.get("Wheel"));
    }
}
