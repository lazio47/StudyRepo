package aula04.Exercicio4_1;

public class Triangulo {
    private Double lado1;
    private Double lado2;
    private Double lado3;

    public Triangulo(Double lado1, Double lado2, Double lado3){
        assert lado1+lado2>lado3;
        assert lado1+lado3>lado2;
        assert lado2+lado3>lado1;
        this.lado1 = lado1;
        this.lado2 = lado2;
        this.lado3 = lado3;
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

    public Double Perimetro(){
        return lado1+lado2+lado3;
    }


    public Double Area(){
        return Math.sqrt((Perimetro()/2)*(Perimetro()/2-lado1)*(Perimetro()/2-lado2)*(Perimetro()/2-lado3));
    }

    public String toString(){
        return "O valor da área é "+Area()+" e do perimetro é "+Perimetro()+".";
    }
}
