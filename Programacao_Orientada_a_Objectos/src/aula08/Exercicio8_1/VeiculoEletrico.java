package aula08.Exercicio8_1;

public interface VeiculoEletrico {
    int autonomia(); // devolve autonomia restante
    void carregar(int percentagem); // simula um carregamento até ‘percentagem’
}
