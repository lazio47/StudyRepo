package aula08.Exercicio8_1;

public class Motociclo extends Veiculo {

    private Tipo tipo;

    public Motociclo(String matricula, String marca, String modelo, double potencia, Tipo tipo) {
        super(matricula, marca, modelo, potencia);
        this.tipo = tipo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    
}
