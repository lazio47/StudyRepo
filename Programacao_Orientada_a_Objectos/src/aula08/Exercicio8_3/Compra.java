package aula08.Exercicio8_3;

public interface Compra {
    void adicionarProduto (Produto produto, int quantidade); 
    void listarProdutos(); 
    double calcularTotal();
}
