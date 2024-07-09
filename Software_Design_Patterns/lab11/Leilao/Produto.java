import java.util.ArrayList;
import java.util.List;

public class Produto {
    private static int counter = 0;
    private int codigo;
    private String descricao;
    private double precoBase;
    private double precoAtual;
    private StateInterface estadoAtual;
    private List<Observer> observers;

    public Produto(String descricao, double precoBase) {
        this.codigo = counter++;
        this.descricao = descricao;
        this.precoBase = precoBase;
        this.precoAtual = 0;
        this.estadoAtual = new StockState();
        this.observers = new ArrayList<>();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    public void setEstadoAtual(StateInterface estadoAtual) {
        this.estadoAtual = estadoAtual;
        notifyObservers("Produto " + descricao + " mudou para o estado " + estadoAtual.getEstado());
    }

    public void iniciarLeilao(int duracaoEmSegundos) {
        estadoAtual.iniciarLeilao(this, duracaoEmSegundos);
    }

    public void licitar(double valor, Cliente cliente) {
        estadoAtual.licitar(this, valor, cliente);
        notifyObservers("Produto " + descricao + " recebeu uma licitação de " + valor + " por " + cliente.getNome());
    }

    public void finalizarLeilao() {
        estadoAtual.finalizarLeilao(this);
    }

    public void vender() {
        estadoAtual.vender(this);
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPrecoBase() {
        return precoBase;
    }

    public double getPrecoAtual() {
        return precoAtual;
    }

    public void setPrecoAtual(double precoAtual) {
        this.precoAtual = precoAtual;
    }
}
