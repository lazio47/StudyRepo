import java.util.Timer;
import java.util.TimerTask;

public class LeilaoState implements StateInterface {
    private long tempoFinalLeilao;
    private Timer timer;
    private Produto produto; 

    public LeilaoState(Produto produto, int duracaoEmSegundos) {
        this.produto = produto;
        this.tempoFinalLeilao = System.currentTimeMillis() + (duracaoEmSegundos * 1000);
        this.timer = new Timer();
        iniciarTemporizador(duracaoEmSegundos * 1000);
    }

    private void iniciarTemporizador(long delayMilissegundos) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (produto != null) {
                    produto.finalizarLeilao();
                    timer.cancel(); 
                }
            }
        }, delayMilissegundos);
    }

    @Override
    public void iniciarLeilao(Produto produto, int duracaoEmSegundos) {
        System.out.println("Produto " + produto.getDescricao() + " já está em leilão.");
    }

    @Override
    public void licitar(Produto produto, double valor, Cliente cliente) {
        if (System.currentTimeMillis() < tempoFinalLeilao && valor > produto.getPrecoAtual()) {
            produto.setPrecoAtual(valor);
            produto.notifyObservers("Nova licitação para o produto " + produto.getDescricao() + " de " + valor + " por " + cliente.getNome());
        } else {
            System.out.println("Licitação inválida para o produto " + produto.getDescricao());
        }
    }

    @Override
    public void finalizarLeilao(Produto produto) {
        if (produto.getPrecoAtual() > 0) {
            produto.setEstadoAtual(new VendasState());
            produto.notifyObservers("Produto " + produto.getDescricao() + " foi vendido por " + produto.getPrecoAtual());
        } else {
            produto.setEstadoAtual(new StockState());
            produto.notifyObservers("Produto " + produto.getDescricao() + " não teve licitações e voltou ao estoque.");
        }
    }

    @Override
    public void vender(Produto produto) {
        System.out.println("Produto " + produto.getDescricao() + " está em leilão. Não pode ser vendido diretamente.");
    }

    @Override
    public String getEstado() {
        return "Leilao";
    }
}
