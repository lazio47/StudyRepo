package aula04.Exercicio4_1;

public class Circulo {
    private Double Raio;
    
    public Circulo(Double Raio){
        this.Raio = Raio;
    }
    public void setRaio(Double Raio){
        assert Raio>0;
        this.Raio = Raio;
    }

    public Double getRaio(){
        return Raio;
    }

    public Double Perimetro(){
        return 2*Raio*Math.PI;
    }

    public Double Area(){
        return Math.PI*Math.pow(Raio, 2);
    }

    public String toString(){
        return "Circulo: "+Raio+"O valor da área é "+Area()+" e do perimetro é "+Perimetro()+".";
    }

}
