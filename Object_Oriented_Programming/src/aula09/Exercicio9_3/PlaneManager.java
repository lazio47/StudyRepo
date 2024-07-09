package aula09.Exercicio9_3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlaneManager {

    Map<String, Plane> planes = new HashMap<>();
    List<CommercialPlane> comercial = new ArrayList<>();
    List<MilitaryPlane> militar = new ArrayList<>();

    
    public void addPlane(Plane plane){
        planes.put(plane.getId(), plane);
        if(plane instanceof CommercialPlane){
            comercial.add((CommercialPlane)plane);
        }else if(plane instanceof MilitaryPlane){
            militar.add((MilitaryPlane)plane);
        }
    }

    public void removePlane(String id){
        planes.remove(id);
    }

    public Plane searchPlane(String id){
        return planes.containsKey(id) ? planes.get(id) : null;
    }

    public List<CommercialPlane> getCommercialPlanes(){
        return comercial;
    }
    public List<MilitaryPlane> getMilitaryPlanes(){
        return militar;
    }

    public void printAllPlanes(){
        for(Map.Entry<String, Plane> set :
        planes.entrySet()){
            System.out.println(set.getValue());
        }
    }

    public Plane getFastestPlane(){
        int i = 0;
        Plane p = null;
        for(Map.Entry<String, Plane> set :
        planes.entrySet()){
            if(i==0){
                p = set.getValue();
            }else if(p.getMaxSpeed()<set.getValue().getMaxSpeed()){ 
                p = set.getValue();
            }
        }
        return p;
    }

}
