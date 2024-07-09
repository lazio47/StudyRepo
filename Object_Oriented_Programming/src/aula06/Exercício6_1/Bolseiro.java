package aula06.Exercício6_1;

import aula05.DateYMD;

public class Bolseiro extends Aluno {

    private Professor Orientador;
    private float Bolsa;
    private int Mec;
    public Bolseiro(String nome, int cc, DateYMD dataNasc, Professor Orientador, float Bolsa) {
        super(nome, cc, dataNasc);
        this.Orientador = Orientador;
        this.Bolsa = Bolsa;
    }
    public Professor getOrientador() {
        return Orientador;
    }
    public float getBolsa() {
        return Bolsa;
    }
    public int getNMec(){
        super.getNMec();
        return super.getNMec()-1;
    }
    @Override
    public String toString() {
        return "Nome: "+getName()+ "; Orientador: " + Orientador.getName() + "; Bolsa: " + Bolsa + ";";
    }
    public void setOrientador(Professor orientador) {
        Orientador = orientador;
    }
    public void setBolsa(float bolsa) {
        Bolsa = bolsa;
    }
    
}
