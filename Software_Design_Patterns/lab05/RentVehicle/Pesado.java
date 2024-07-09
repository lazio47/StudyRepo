
public class Pesado extends Veiculo {
    
    private String numeroQuadro;
    private int peso;
    
    public Pesado(String matricula, String marca, String modelo, int potencia, String numeroQuadro, int peso) {
        super(matricula, marca, modelo, potencia);
        this.numeroQuadro = numeroQuadro;
        this.peso = peso;
    }

    public String getNumeroQuadro() {
        return numeroQuadro;
    }

    public void setNumeroQuadro(String numeroQuadro) {
        this.numeroQuadro = numeroQuadro;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

