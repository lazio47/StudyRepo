package aula04.Exercicio4_1;

public class Retangulo {
    private Double comprimento;
    private Double altura;

    public Retangulo(Double comprimento, Double altura){
        assert comprimento>0;
        assert altura>0;
        this.comprimento = comprimento;
        this.altura = altura;
    }

    public void setcomprimento(Double comprimento){
        assert comprimento>0;
        this.comprimento = comprimento;
    }

    public Double getcomprimento(){
       return comprimento;
    }

    public void setaltura(Double altura){
        assert altura>0;
        this.altura = altura;
    }

    public Double getaltura(){
       return altura;
    }

    public Double Perimetro(){
        return 2*altura+2*comprimento;
    }

    public Double Area(){
        return comprimento*altura;
    }

    public String toString(){
        return "O valor da área é "+Area()+" e do perimetro é "+Perimetro()+".";
    }    
}
