
public class Taxi extends AutomovelLigeiro{

    private String numeroLicenca;

    public Taxi(String matricula, String marca, String modelo, int potencia, String numeroQuadro,
            int capacidadeBagageira, String numeroLicenca) {
        super(matricula, marca, modelo, potencia, numeroQuadro, capacidadeBagageira);
        this.numeroLicenca = numeroLicenca;
    }

    public String getNumeroLicenca() {
        return numeroLicenca;
    }

    public void setNumeroLicenca(String numeroLicenca) {
        this.numeroLicenca = numeroLicenca;
    }

    @Override
    public String toString() {
        return "Taxi "+super.toString()+" numeroLicenca= "+numeroLicenca;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
    
}
