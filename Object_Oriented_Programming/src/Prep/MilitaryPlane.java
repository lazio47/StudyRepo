package Prep;

public class MilitaryPlane extends Plane{
    private int numMunicoes;

    public MilitaryPlane(String id, String fabricante, String modelo, int year, int maxPassageiros, int maxVelocidade,
            int numMunicoes) {
        super(id, fabricante, modelo, year, maxPassageiros, maxVelocidade);
        this.numMunicoes = numMunicoes;
    }

    public int getNumMunicoes() {
        return numMunicoes;
    }

    public void setNumMunicoes(int numMunicoes) {
        this.numMunicoes = numMunicoes;
    }
    public String getPlaneType(){
        return "Militar";
    }
    @Override
    public String toString() {
        return super.toString()+" [numMunicoes=" + numMunicoes + "]";
    }
}
