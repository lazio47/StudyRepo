import java.util.ArrayList;
import java.util.List;

class TorreDeControle implements Mediator {
    private List<Aeronave> aeronaves;

    public TorreDeControle() {
        this.aeronaves = new ArrayList<>();
    }

    public void registrarAeronave(Aeronave aeronave) {
        aeronaves.add(aeronave);
    }

    @Override
    public void sendMessage(String message, Aeronave aeronave) {
        for (Aeronave a : aeronaves) {
            if (a != aeronave) {
                a.receiveMessage(message);
            }
        }
    }
}