package be.sandervl.raytracer.services.reader;


import be.sandervl.raytracer.business.math.Vector3D;
import be.sandervl.raytracer.business.objects.renderables.Renderable;
import be.sandervl.raytracer.business.objects.renderables.Triangle;
import be.sandervl.raytracer.business.scene.Color;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ModelReaderServiceImpl implements ModelReaderService {

    @Override
    public Set<Renderable> readModel(File file) {
        List<Vector3D> vertices = new ArrayList<Vector3D>();
        List<Vector3D> normals = new ArrayList<Vector3D>();
        Set<Renderable> result = new HashSet<Renderable>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line;
            while ((line = br.readLine()) != null) {
                String command = line.split("\\s")[0];
               if(command.equals("v")){
                        String[] coords = line.split("\\s");
                        if (coords.length == 4) {
                            vertices.add(new Vector3D(Float.parseFloat(coords[1]), Float.parseFloat(coords[2]), Float.parseFloat(coords[3])));
                        }
               } else if(command.equals("vn")){
                        String[] nCoords = line.split("\\s");
                        if (nCoords.length == 4) {
                            normals.add(new Vector3D(Float.parseFloat(nCoords[1]), Float.parseFloat(nCoords[2]), Float.parseFloat(nCoords[3])));
                        }
               } else if (command.equals("f")){
                        String[] indices = line.split("\\s");
                        if(indices.length == 4){
                            String indexA = indices[1];
                            Vector3D a = vertices.get(Integer.parseInt(indexA.split("/")[0])-1);
                            String indexB = indices[2];
                            Vector3D b = vertices.get(Integer.parseInt(indexB.split("/")[0])-1);
                            String indexC = indices[3];
                            Vector3D c = vertices.get(Integer.parseInt(indexC.split("/")[0])-1);
                            Vector3D n = normals.get(Integer.parseInt(indexA.split("/")[2])-1);
                            n.normalize();
                            Triangle triangle = new Triangle(a,b,c,n,new Color(1,1,1));
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
}
