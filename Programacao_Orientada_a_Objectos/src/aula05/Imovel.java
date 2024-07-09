package aula05;

import java.time.LocalDate;
public class Imovel {
    private int numQuartos;
    private String localidade;
    private double preco;
    private boolean disponiblidade;
    private LocalDate inicioLeilao;
    private LocalDate fimLeilao;
    private int id;

    private static int proximoid =  1000;

    public Imovel(int numQuartos, String localidade, double preco, boolean disponiblidade, LocalDate inicioLeilao,
            LocalDate fimLeilao) {
        this.numQuartos = numQuartos;
        this.localidade = localidade;
        this.preco = preco;
        this.disponiblidade = disponiblidade;
        this.inicioLeilao = inicioLeilao;
        this.fimLeilao = fimLeilao;
        this.id  = proximoid++;

    }

    public int getNumQuartos() {
        return numQuartos;
    }

    public String getLocalidade() {
        return localidade;
    }

    public double getPreco() {
        return preco;
    }

    public boolean isDisponiblidade() {
        return disponiblidade;
    }

    public LocalDate getInicioLeilao() {
        return inicioLeilao;
    }

    public LocalDate getFimLeilao() {
        return fimLeilao;
    }

    public int getId() {
        return id;
    }

    public static int getProximoid() {
        return proximoid;
    }

    public String toString(){
        String str = "imovel: " + id + "Quartos: " + numQuartos + "localidade: " + localidade + "preço:" + preco + "disponivel: " +(disponiblidade ? "sim": "nao" );
        if(inicioLeilao != null && fimLeilao != null){
            str += "leilao" + inicioLeilao.toString() + ":" + fimLeilao.toString();
        }
        return str;
    }
    

    
    



    
}
