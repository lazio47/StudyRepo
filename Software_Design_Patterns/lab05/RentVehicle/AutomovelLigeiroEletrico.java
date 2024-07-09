
public class AutomovelLigeiroEletrico extends AutomovelLigeiro implements VeiculoEletrico {

    private int autonomia;

    public AutomovelLigeiroEletrico(String matricula, String marca, String modelo, int potencia, String numeroQuadro,
            int capacidadeBagageira) {
        super(matricula, marca, modelo, potencia, numeroQuadro, capacidadeBagageira);
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
        return "Automovel Ligeiro Eletrico "+super.toString()+" autonomia=" + autonomia;
    } 
}
