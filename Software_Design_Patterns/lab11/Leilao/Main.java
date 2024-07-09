public class Main {
    public static void main(String[] args) {
        SistemaLeiloes sistema = new SistemaLeiloes();

        Produto p1 = new Produto("Laptop", 1500);
        Produto p2 = new Produto("Smartphone", 800);
        Produto p3 = new Produto("Livro", 50);

        sistema.adicionarProduto(p1);
        sistema.adicionarProduto(p2);
        sistema.adicionarProduto(p3);

        Cliente c1 = new Cliente("Alice");
        Cliente c2 = new Cliente("Bob");
        Cliente c3 = new Cliente("Carol");

        sistema.adicionarCliente(c1);
        sistema.adicionarCliente(c2);
        sistema.adicionarCliente(c3);

        Gestor gestor = new Gestor("Gestor1");
        sistema.setGestor(gestor);

        sistema.iniciarLeilao(p1, 10);
        sistema.iniciarLeilao(p2, 20);

        sistema.licitar(p1, 1600, c1);
        sistema.licitar(p2, 850, c2);

        sistema.licitar(p3, 60, c3); 
        sistema.licitar(p1, 1400, c3); 

        sistema.finalizarLeilao(p1);
        sistema.finalizarLeilao(p2);
    }
}
