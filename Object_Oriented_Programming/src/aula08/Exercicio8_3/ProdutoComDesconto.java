package aula08.Exercicio8_3;

public class ProdutoComDesconto extends ProdutoGenerico{
    private double desconto;

    public ProdutoComDesconto(String nome, int quantidade, double preco, double desconto) {
        super(nome, quantidade, preco);
        this.desconto = desconto;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }
    
}
