package aula08.Exercicio8_1;

public class PesadoMercadorias extends Pesado {
    
    private double cargaMaxima;

    public PesadoMercadorias(String matricula, String marca, String modelo, double potencia, int numeroQuadro,
            double peso, double cargaMaxima) {
        super(matricula, marca, modelo, potencia, numeroQuadro, peso);
        this.cargaMaxima = cargaMaxima;
    }

    public double getCargaMaxima() {
        return cargaMaxima;
    }

    public void setCargaMaxima(double cargaMaxima) {
        this.cargaMaxima = cargaMaxima;
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
