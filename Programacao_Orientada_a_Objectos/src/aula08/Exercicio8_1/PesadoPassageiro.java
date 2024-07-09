package aula08.Exercicio8_1;

public class PesadoPassageiro extends Pesado {
    
    private int numMaxPassageiro;

    public PesadoPassageiro(String matricula, String marca, String modelo, double potencia, int numeroQuadro,
            double peso, int numMaxPassageiro) {
        super(matricula, marca, modelo, potencia, numeroQuadro, peso);
        this.numMaxPassageiro = numMaxPassageiro;
    }

    public int getNumMaxPassageiro() {
        return numMaxPassageiro;
    }

    public void setNumMaxPassageiro(int numMaxPassageiro) {
        this.numMaxPassageiro = numMaxPassageiro;
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
