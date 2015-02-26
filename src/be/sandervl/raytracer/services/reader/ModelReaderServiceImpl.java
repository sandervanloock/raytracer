package be.sandervl.raytracer.services.reader;


import be.sandervl.raytracer.business.math.Vector3D;
import be.sandervl.raytracer.business.objects.renderables.Material;
import be.sandervl.raytracer.business.objects.renderables.Renderable;
import be.sandervl.raytracer.business.objects.renderables.Texture;
import be.sandervl.raytracer.business.objects.renderables.Triangle;
import be.sandervl.raytracer.business.scene.Color;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

public class ModelReaderServiceImpl implements ModelReaderService {

    private static final Color DEFAULT_COLOR = new Color(0.5f, 0.5f, 0.5f);
    private static final Material DEFAULT_MATERIAL = new Material(0.5f, 0.2f, 0.5f, 16, null);

    @Override
    public Set<Renderable> readModel(File file) {
        List<Vector3D> vertices = new ArrayList<Vector3D>();
        List<Vector3D> normals = new ArrayList<Vector3D>();
        List<Vector3D> textureNormals = new ArrayList<Vector3D>();
        Set<Renderable> result = new HashSet<Renderable>();
        Map<String, Material> materials = new HashMap<String, Material>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line;
            Material material = DEFAULT_MATERIAL;
            while ((line = br.readLine()) != null) {
                String[] split = line.split("\\s+");
                String command = split[0];
                if (command.equals("mtllib")) {
                    materials = readMaterials(new File(file.getParentFile().getPath() + split[1]));
                } else if (command.equals("v")) {
                    String[] coords = line.split("\\s+");
                    if (coords.length == 4) {
                        vertices.add(new Vector3D(Float.parseFloat(coords[1]), Float.parseFloat(coords[2]), Float.parseFloat(coords[3])));
                    }
                } else if (command.equals("vn")) {
                    String[] nCoords = line.split("\\s+");
                    if (nCoords.length == 4) {
                        Vector3D n = new Vector3D(Float.parseFloat(nCoords[1]), Float.parseFloat(nCoords[2]), Float.parseFloat(nCoords[3]));
                        n.normalize();
                        normals.add(n.multipy(-1));
                    }
                } else if (command.equals("vt")) {
                    String[] nCoords = line.split("\\s+");
                    if (nCoords.length == 4) {
                        Vector3D vt = new Vector3D(Float.parseFloat(nCoords[1]), Float.parseFloat(nCoords[2]), Float.parseFloat(nCoords[3]));
                        textureNormals.add(vt);
                    }
                } else if (command.equals("usemtl") && materials.containsKey(split[1])) {
                    material = materials.get(split[1]);
                } else if (command.equals("f")) {
                    String[] indices = line.split("\\s+");
                    if (indices.length == 4) {
                        String indexA = indices[1];
                        String[] splitA = indexA.split("/");
                        Vector3D a = vertices.get(Integer.parseInt(splitA[0]) - 1);
                        Vector3D ta = textureNormals.get(Integer.parseInt(splitA[1]) - 1);
                        Vector3D na = normals.get(Integer.parseInt(splitA[2]) - 1);
                        String indexB = indices[2];
                        String[] splitB = indexB.split("/");
                        Vector3D b = vertices.get(Integer.parseInt(splitB[0]) - 1);
                        Vector3D tb = textureNormals.get(Integer.parseInt(splitB[1]) - 1);
                        Vector3D nb = normals.get(Integer.parseInt(splitB[2]) - 1);
                        String indexC = indices[3];
                        String[] splitC = indexC.split("/");
                        Vector3D c = vertices.get(Integer.parseInt(splitC[0]) - 1);
                        Vector3D tc = textureNormals.get(Integer.parseInt(splitC[1]) - 1);
                        Vector3D nc = normals.get(Integer.parseInt(splitC[2]) - 1);
                        Triangle triangle = new Triangle(a, b, c, na, nb, nc, ta, tb, tc, material,null);
                        result.add(triangle);
                    }
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Map<String, Material> readMaterials(File file) {
        Map<String, Material> result = new HashMap<String, Material>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line, name;
            Material material = new Material();
            while ((line = br.readLine()) != null) {
                String[] split = line.split("\\s+");
                String command = split[0];
                if (command.equals("newmtl")) {
                    material = new Material();
                    result.put(split[1], material);
                } else if (command.equals("Ns")) {
                    material.setNs(Float.parseFloat(split[1]));
                } else if (command.equals("Ka")) {
                    material.setKa(Float.parseFloat(split[1]));
                } else if (command.equals("Kd")) {
                    material.setKd(Float.parseFloat(split[1]));
                } else if (command.equals("Ks")) {
                    material.setKs(Float.parseFloat(split[1]));
                } else if (command.equals("map_Kd")) {
                    File imageFile = new File(file.getParentFile().getPath() + "/Texture/" + split[1]);
                    BufferedImage texture = ImageIO.read(imageFile);
                    material.setTexture(new Texture(texture));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
