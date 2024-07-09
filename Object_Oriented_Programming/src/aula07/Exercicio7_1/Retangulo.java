package aula07.Exercicio7_1;

public class Retangulo extends Forma {
    private double comprimento;
    private double altura;
    private String cor;

    public Retangulo(Double comprimento, Double altura, String cor){
        assert comprimento>0;
        assert altura>0;
        this.comprimento = comprimento;
        this.altura = altura;
        this.cor = cor;
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

    public double Perimetro(){
        return 2*altura+2*comprimento;
    }

    public double Area(){
        return comprimento*altura;
    }

    public String toString(){
        return "Retângulo: O valor da área é "+Area()+" e do perimetro é "+Perimetro()+".";
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Retangulo other = (Retangulo) obj;
        if (Double.doubleToLongBits(comprimento) != Double.doubleToLongBits(other.comprimento))
            return false;
        if (Double.doubleToLongBits(altura) != Double.doubleToLongBits(other.altura))
            return false;
        if (cor == null) {
            if (other.cor != null)
                return false;
        } else if (!cor.equals(other.cor))
            return false;
        return true;
    }    
}
