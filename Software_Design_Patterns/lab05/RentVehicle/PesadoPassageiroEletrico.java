
public class PesadoPassageiroEletrico extends PesadoPassageiro implements VeiculoEletrico {

    private int autonomia;

    public PesadoPassageiroEletrico(String matricula, String marca, String modelo, int potencia, String numeroQuadro,
            int peso, int numMaxPassageiro) {
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

    @Override
    public String toString() {
        return "Pesado de Passageiro Eletrico"+super.toString()+"autonomia=" + autonomia;
    }

    
    
}
