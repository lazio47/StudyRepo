package aula04.Exercicio4_2;

import java.util.ArrayList;

class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public double getTotalValue() {
        return price * quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int  getQuantity() {
        return quantity;
    }
}


class CashRegister {

    // TODO: completar implementação da classe
    Product[] produtos = new Product[5];
    public ArrayList<Product> lista = new ArrayList<Product>();
    public void addProduct(Product product) {
        lista.add(product);
    }
    
    @Override
    public String toString() {
        String str = "";
        String s = String.format("%-20s %-10s %-13s %s\n", "Product","Price", "Quantity", "Total");
        str += s;
        //System.out.println(str);
        //String tabela = "";
        for (Product p : lista) {
            s = String.format("%-20s %-10f %-13d %f\n", p.getName(), p.getPrice(), p.getQuantity(), p.getTotalValue());
            str+= s;
          }

        return str;
    }

}

public class CashRegisterDemo {

    public static void main(String[] args) {

        // Cria e adiciona 5 produtos
        CashRegister cr = new CashRegister();
        cr.addProduct(new Product("Book", 9.99, 3));
        cr.addProduct(new Product("Pen", 1.99, 10));
        cr.addProduct(new Product("Headphones", 29.99, 2));
        cr.addProduct(new Product("Notebook", 19.99, 5));
        cr.addProduct(new Product("Phone case", 5.99, 1));
        
        // TODO: Listar o conteúdo e valor total
        System.out.println(cr);

        

    }
}