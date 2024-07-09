package aula08.Exercicio8_3;

import java.util.HashMap;

public class CarrinhoDeCompras implements Compra{

    public double total = 0;

    HashMap<Produto, Integer> produtos = new HashMap<Produto, Integer>();
    @Override
    public void adicionarProduto(Produto produto, int quantidade) {
        produtos.put(produto, quantidade);
    }

    @Override
    public void listarProdutos() {
        System.out.printf("%-15s %9s %10s\n", "Produto", "Quantidade", "Preço");
        System.out.println("--------------------------------------");
        for(Produto i:produtos.keySet()){
            System.out.printf("%-15s %7s %13s\n", i.getNome(), produtos.get(i), i.getPreco());
        }
        System.out.println("--------------------------------------");
    }

    @Override
    public double calcularTotal() {
        for(Produto i:produtos.keySet()){
            if (i instanceof ProdutoComDesconto){
                total += (i.getPreco()-(((ProdutoComDesconto)i).getDesconto()*i.getPreco()/100))*produtos.get(i);
            }else if(i instanceof ProdutoGenerico){
                total += i.getPreco()*produtos.get(i);
            }
        }
        return total;
    }
    
}
