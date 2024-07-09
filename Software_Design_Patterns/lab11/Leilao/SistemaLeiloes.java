import java.util.ArrayList;
import java.util.List;

public class SistemaLeiloes {
    private List<Produto> produtos;
    private List<Cliente> clientes;
    private Gestor gestor;

    public SistemaLeiloes() {
        this.produtos = new ArrayList<>();
        this.clientes = new ArrayList<>();
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void setGestor(Gestor gestor) {
        this.gestor = gestor;
    }

    public void iniciarLeilao(Produto produto, int duracaoEmSegundos) {
        produto.iniciarLeilao(duracaoEmSegundos);
    }

    public void licitar(Produto produto, double valor, Cliente cliente) {
        produto.licitar(valor, cliente);
    }

    public void finalizarLeilao(Produto produto) {
        produto.finalizarLeilao();
    }

    public void visualizarProdutos() {
        for (Produto p : produtos) {
            System.out.println(p);
        }
    }
}