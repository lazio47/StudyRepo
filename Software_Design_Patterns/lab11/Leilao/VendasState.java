public class VendasState implements StateInterface {

    @Override
    public void iniciarLeilao(Produto produto, int duracaoEmSegundos) {
        System.out.println("Produto " + produto.getDescricao() + " já foi vendido. Não é possível iniciar leilão.");
    }

    @Override
    public void licitar(Produto produto, double valor, Cliente cliente) {
        System.out.println("Produto " + produto.getDescricao() + " já foi vendido. Não é possível licitar.");
    }

    @Override
    public void finalizarLeilao(Produto produto) {
        System.out.println("Produto " + produto.getDescricao() + " já foi vendido. Não há leilão a finalizar.");
    }

    @Override
    public void vender(Produto produto) {
        System.out.println("Produto " + produto.getDescricao() + " já foi vendido.");
    }

    @Override
    public String getEstado() {
        return "Vendas";
    }
}
