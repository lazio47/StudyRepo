package aula09.Exercicio9_3;

public class MilitaryPlane extends Plane {
    private int numMissiles;

    public MilitaryPlane(String id, String manufacturer, String model, int year, int maxPassengers, double maxSpeed, int numMissiles) {
        super(id, manufacturer, model, year, maxPassengers, maxSpeed);
        this.numMissiles = numMissiles;
    }

    public int getNumMissiles() {
        return numMissiles;
    }

    public void setNumMissiles(int numMissiles) {
        this.numMissiles = numMissiles;
    }
    
    public String getPlaneType(){
        return "Militar";
    }

    @Override
    public String toString(){
        return "[Type: "+getPlaneType() +super.toString() + ", Missiles: "+getNumMissiles()+"]";
    }
}