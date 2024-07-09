package Prep;

public class CommercialPlane extends Plane{
    private int numTripulantes;
    public CommercialPlane(String id, String fabricante, String modelo, int year, int maxPassageiros,
            int maxVelocidade, int numTripulantes) {
        super(id, fabricante, modelo, year, maxPassageiros, maxVelocidade);
        this.numTripulantes = numTripulantes;
    }
    public int getNumTripulantes() {
        return numTripulantes;
    }
    public String getPlaneType(){
        return "Comercial";
    }
    public void setNumTripulantes(int numTripulantes) {
        this.numTripulantes = numTripulantes;
    }
    @Override
    public String toString() {
        return super.toString()+" [numTripulantes=" + numTripulantes + "]";
    }
    
    
}
