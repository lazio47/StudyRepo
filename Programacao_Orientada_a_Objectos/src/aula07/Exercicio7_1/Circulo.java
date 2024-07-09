package aula07.Exercicio7_1;

public class Circulo extends Forma {
    private double Raio;
    private String cor;
    
    public Circulo(Double Raio, String cor){
        this.Raio = Raio;
        this.cor = cor;
    }
    public void setRaio(Double Raio){
        assert Raio>0;
        this.Raio = Raio;
    }

    public Double getRaio(){
        return Raio;
    }

    public double Perimetro(){
        return 2*Raio*Math.PI;
    }

    public double Area(){
        return Math.PI*Math.pow(Raio, 2);
    }

    public String toString(){
        return "Circulo: "+Raio+"O valor da área é "+Area()+" e do perimetro é "+Perimetro()+".";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Circulo other = (Circulo) obj;
        if (Double.doubleToLongBits(Raio) != Double.doubleToLongBits(other.Raio))
            return false;
        if (cor == null) {
            if (other.cor != null)
                return false;
        } else if (!cor.equals(other.cor))
            return false;
        return true;
    }

}
