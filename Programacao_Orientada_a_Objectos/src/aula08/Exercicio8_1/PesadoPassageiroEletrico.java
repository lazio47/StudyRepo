package aula08.Exercicio8_1;

public class PesadoPassageiroEletrico extends PesadoPassageiro implements VeiculoEletrico {

    private int autonomia;

    public PesadoPassageiroEletrico(String matricula, String marca, String modelo, double potencia, int numeroQuadro,
            double peso, int numMaxPassageiro) {
        super(matricula, marca, modelo, potencia, numeroQuadro, peso, numMaxPassageiro);
    }

    @Override
    public int autonomia() {
        return autonomia;
    }

    @Override
    public void carregar(int percentagem) {
        if((this.autonomia+percentagem)<=100){
            this.autonomia+=percentagem;
        }else{
            this.autonomia=100;
        }
    }

    
    
}
