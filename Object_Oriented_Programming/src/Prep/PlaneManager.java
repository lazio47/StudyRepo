package Prep;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class PlaneManager {
    Map<String, Plane> planes = new HashMap<>();
    public void addPlane(Plane plane){
        planes.put(plane.getId(), plane);
    }
    public void removePlane(String id){
        if(planes.containsKey(id)){
            planes.remove(id);
        }
    }
    public Plane searchPlane(String id){
        if(planes.containsKey(id)){
            return planes.get(id);
        }
        return null;
    }
    public List<Plane> getCommercialPlanes(){
        List<Plane> commercial = new ArrayList<Plane>();
        for(Entry<String , Plane> set: planes.entrySet()){
            if(set.getValue() instanceof CommercialPlane){
                commercial.add(set.getValue());
            }
        }
        return commercial;
    }
    public List<Plane> getMilitaryPlanes(){
        List<Plane> militar = new ArrayList<Plane>();
        for(Entry<String , Plane> set: planes.entrySet()){
            if(set.getValue() instanceof MilitaryPlane){
                militar.add(set.getValue());
            }
        }
        return militar;
    }
    public void printAllPlanes(){
        for(Entry<String, Plane> set : planes.entrySet()){
            System.out.println(set.getValue().toString());
        }
    }
    public Plane getFastestPlane(){
        Plane fast = new CommercialPlane("null", "null", "null", 2000, 0, 0, 0);
        for(Entry<String, Plane> set : planes.entrySet()){
            if(set.getValue().getMaxVelocidade()>fast.getMaxVelocidade()){
                fast = set.getValue();
            }
        }
        return fast;
    }
}

//{(str1, plane1), (str2, plane2), ..., (strn, planen)}
