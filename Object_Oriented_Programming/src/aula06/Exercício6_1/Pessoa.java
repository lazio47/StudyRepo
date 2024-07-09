package aula06.Exercício6_1;

import aula05.DateYMD;

public class Pessoa {
    private String Name;
    private int cc;
    private DateYMD dataNasc;
    public Pessoa(String Name, int cc, DateYMD dataNasc) {
        this.Name = Name;
        this.cc = cc;
        this.dataNasc = dataNasc;
    }
    public String getName() {
        return Name;
    }
    public int getCc() {
        return cc;
    }
    public DateYMD getDataNasc() {
        return dataNasc;
    }
    @Override
    public String toString() {
        return  Name + "; CC: " + cc + "; Data de Nascimento: " + dataNasc.getDay()+"/"+dataNasc.getMonth()+"/"+dataNasc.getYear();
    }
    
}
