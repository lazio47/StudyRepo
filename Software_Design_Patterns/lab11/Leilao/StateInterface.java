public interface StateInterface {
    void iniciarLeilao(Produto produto, int duracaoEmSegundos);
    void licitar(Produto produto, double valor, Cliente cliente);
    void finalizarLeilao(Produto produto);
    void vender(Produto produto);
    String getEstado();
}
