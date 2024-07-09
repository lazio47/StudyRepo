package aula08.Exercicio8_1;

public class Taxi extends AutomovelLigeiro{

    private int numeroLicenca;

    public Taxi(String matricula, String marca, String modelo, double potencia, int numeroQuadro,
            double capacidadeBagageira, int numeroLicenca) {
        super(matricula, marca, modelo, potencia, numeroQuadro, capacidadeBagageira);
        this.numeroLicenca = numeroLicenca;
    }

    public int getNumeroLicenca() {
        return numeroLicenca;
    }

    public void setNumeroLicenca(int numeroLicenca) {
        this.numeroLicenca = numeroLicenca;
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
