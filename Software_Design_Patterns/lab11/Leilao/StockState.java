public class StockState implements StateInterface {

    @Override
    public void iniciarLeilao(Produto produto, int duracaoEmSegundos) {
        produto.setEstadoAtual(new LeilaoState(produto, duracaoEmSegundos));
        System.out.println("Produto " + produto.getDescricao() + " agora está em leilão.");
    }

    @Override
    public void licitar(Produto produto, double valor, Cliente cliente) {
        System.out.println("Produto " + produto.getDescricao() + " está em stock. Não é possível licitar.");
    }

    @Override
    public void finalizarLeilao(Produto produto) {
        System.out.println("Produto " + produto.getDescricao() + " está em stock. Não há leilão a finalizar.");
    }

    @Override
    public void vender(Produto produto) {
        System.out.println("Produto " + produto.getDescricao() + " está em stock. Não pode ser vendido diretamente.");
    }

    @Override
    public String getEstado() {
        return "Stock";
    }
}
