package aula08.Exercicio8_1;

public class AutomovelLigeiro extends Veiculo {

    private int numeroQuadro;
    private double capacidadeBagageira;

    public AutomovelLigeiro(String matricula, String marca, String modelo, double potencia, int numeroQuadro, double capacidadeBagageira) {
        super(matricula, marca, modelo, potencia);
        this.numeroQuadro = numeroQuadro;
        this.capacidadeBagageira = capacidadeBagageira;
    }

    public int getNumeroQuadro() {
        return numeroQuadro;
    }

    public void setNumeroQuadro(int numeroQuadro) {
        this.numeroQuadro = numeroQuadro;
    }

    public double getCapacidadeBagageira() {
        return capacidadeBagageira;
    }

    public void setCapacidadeBagageira(double capacidadeBagageira) {
        this.capacidadeBagageira = capacidadeBagageira;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
    
}
