package aula09.Exercicio9_3;

public class CommercialPlane extends Plane {
    private int numOfCrewMembers;

    public CommercialPlane(String id, String manufacturer, String model, int year, int maxNumOfPassengers, double maxSpeed, int numOfCrewMembers) {
        super(id, manufacturer, model, year, maxNumOfPassengers, maxSpeed);
        this.numOfCrewMembers = numOfCrewMembers;
    }

    public void setNumOfCrewMembers(int numOfCrewMembers){
        this.numOfCrewMembers = numOfCrewMembers;
    }

    public int getNumOfCrewMembers(){
        return this.numOfCrewMembers;
    }

    public String getPlaneType(){
        return "Comercial";
    }

    @Override
    public String toString(){
        return "[Type: "+getPlaneType() +super.toString() + ", Crew Members: "+getNumOfCrewMembers()+"]";
    }
}
