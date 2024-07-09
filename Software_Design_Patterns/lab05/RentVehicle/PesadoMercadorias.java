
public class PesadoMercadorias extends Pesado {
    
    private double cargaMaxima;

    public PesadoMercadorias(String matricula, String marca, String modelo, int potencia, String numeroQuadro,
            int peso, double cargaMaxima) {
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
        return "Pesado de Mercadorias"+super.toString()+" carga Maxima=" + cargaMaxima;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    
}
