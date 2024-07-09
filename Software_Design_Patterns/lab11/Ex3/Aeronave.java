abstract class Aeronave {
    protected Mediator mediator;
    protected String nome;

    public Aeronave(Mediator mediator) {
        this.mediator = mediator;
    }

    public void sendMessage(String message) {
        System.out.println(nome + " enviando mensagem: " + message);
        mediator.sendMessage(message, this);
    }

    public void receiveMessage(String message) {
        System.out.println(nome + " recebeu mensagem: " + message);
    }
}