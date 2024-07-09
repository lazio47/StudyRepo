public class Cliente implements Observer {
    private String nome;

    public Cliente(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public void update(String message) {
        System.out.println("Cliente " + nome + ": " + message);
    }
}