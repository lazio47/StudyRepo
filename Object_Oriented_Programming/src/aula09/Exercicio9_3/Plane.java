package aula09.Exercicio9_3;

public class Plane {
    private String id;
    private String manufacturer;
    private String model;
    private int year;
    private int maxNumOfPassengers;
    private double maxSpeed;

    public Plane(String id, String manufacturer, String model, int year, int maxNumOfPassengers, double maxSpeed) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
        this.maxNumOfPassengers = maxNumOfPassengers;
        this.maxSpeed = maxSpeed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMaxNumOfPassengers() {
        return maxNumOfPassengers;
    }

    public void setMaxNumOfPassengers(int maxNumOfPassengers) {
        this.maxNumOfPassengers = maxNumOfPassengers;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public String toString() {
        return ", "+ manufacturer + " " + model + ", " + year
                + ", " + maxNumOfPassengers+" Passengers, " + ", Speed " + maxSpeed + "km/h";
    }
}
