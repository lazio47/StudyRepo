import java.util.ArrayList;
import java.util.List;

public class Caixa implements Produto {

    public static int ident = 0;

    private String name;
    private double peso;
    private List<Produto> produtos = new ArrayList<>();

    public Caixa(String name, int peso){
        this.name = name;
        this.peso = peso;
    }

    public double getWeight(){
        double total = peso;
        for (Produto p : produtos){
            total += p.getWeight();
        }
        return total;
    }

    public void add(Produto produto){
        produtos.add(produto);
    }

    @Override
    public void draw() {
        for (int i = 0; i < Caixa.ident; i++){
            System.out.print("\t");
        }
        System.out.println("* Caixa '" + name + " [ Weight: " + peso + " ; Total: " + getWeight() + "]");
        Caixa.ident++;
        for (Produto p : produtos){
            p.draw();
        }
        Caixa.ident--;
    }

}
