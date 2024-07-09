class Aviao extends Aeronave {
    public Aviao(String nome, Mediator mediator) {
        super(mediator);
        this.nome = nome;
    }
}