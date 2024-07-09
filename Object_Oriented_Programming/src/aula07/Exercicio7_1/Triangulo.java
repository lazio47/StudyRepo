package aula07.Exercicio7_1;

public class Triangulo extends Forma {
    private double lado1;
    private double lado2;
    private double lado3;
    private String cor;

    public Triangulo(Double lado1, Double lado2, Double lado3, String cor){
        assert lado1+lado2>lado3;
        assert lado1+lado3>lado2;
        assert lado2+lado3>lado1;
        this.lado1 = lado1;
        this.lado2 = lado2;
        this.lado3 = lado3;
        this.cor = cor;
    }

    public void setlado1(Double lado1){
        assert lado1>0;
        this.lado1 = lado1;
    }

    public Double getlado1(){
       return lado1;
    }

    public void setlado2(Double lado2){
        assert lado2>0;
        this.lado2 = lado2;
    }

    public Double getlado2(){
       return lado2;
    }

    public void setlado3(Double lado3){
        assert lado3>0;
        this.lado3 = lado3;
    }

    public Double getlado3(){
       return lado3;
    }

    public double Perimetro(){
        return lado1+lado2+lado3;
    }


    public double Area(){
        return Math.sqrt((Perimetro()/2)*(Perimetro()/2-lado1)*(Perimetro()/2-lado2)*(Perimetro()/2-lado3));
    }

    public String toString(){
        return "Triângulo: O valor da área é "+Area()+" e do perimetro é "+Perimetro()+".";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Triangulo other = (Triangulo) obj;
        if (Double.doubleToLongBits(lado1) != Double.doubleToLongBits(other.lado1))
            return false;
        if (Double.doubleToLongBits(lado2) != Double.doubleToLongBits(other.lado2))
            return false;
        if (Double.doubleToLongBits(lado3) != Double.doubleToLongBits(other.lado3))
            return false;
        if (cor == null) {
            if (other.cor != null)
                return false;
        } else if (!cor.equals(other.cor))
            return false;
        return true;
    }
}
