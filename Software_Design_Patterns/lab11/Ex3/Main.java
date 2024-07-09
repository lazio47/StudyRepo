public class Main {
    public static void main(String[] args) {
        Mediator mediator = new TorreDeControle();
        Aeronave aviao1 = new Aviao("Boeing 747", mediator);
        Aeronave helicoptero1 = new Helicoptero("Apache", mediator);
        Aeronave aviao2 = new Aviao("Airbus 330", mediator);
        Aeronave helicoptero2 = new Helicoptero("Bell Model 47", mediator);

        ((TorreDeControle) mediator).registrarAeronave(aviao1);
        ((TorreDeControle) mediator).registrarAeronave(helicoptero1);
        ((TorreDeControle) mediator).registrarAeronave(aviao2);
        ((TorreDeControle) mediator).registrarAeronave(helicoptero2);

        aviao1.sendMessage("Solicitando permissão para aterrissar.");
        helicoptero1.sendMessage("Solicitando permissão para decolar.");
    }
}