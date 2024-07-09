package Prep;

public abstract class Plane {
    private String id;
    private String fabricante;
    private String modelo; 
    private int year;
    private int maxPassageiros;
    private int maxVelocidade;
    public Plane(String id, String fabricante, String modelo, int year, int maxPassageiros, int maxVelocidade) {
        this.id = id;
        this.fabricante = fabricante;
        this.modelo = modelo;
        this.year = year;
        this.maxPassageiros = maxPassageiros;
        this.maxVelocidade = maxVelocidade;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getFabricante() {
        return fabricante;
    }
    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public int getMaxPassageiros() {
        return maxPassageiros;
    }
    public void setMaxPassageiros(int maxPassageiros) {
        this.maxPassageiros = maxPassageiros;
    }
    public int getMaxVelocidade() {
        return maxVelocidade;
    }
    public void setMaxVelocidade(int maxVelocidade) {
        this.maxVelocidade = maxVelocidade;
    }
    @Override
    public String toString() {
        return "Plane [id=" + id + ", fabricante=" + fabricante + ", modelo=" + modelo + ", year=" + year
                + ", maxPassageiros=" + maxPassageiros + ", maxVelocidade=" + maxVelocidade + "]";
    }
}
