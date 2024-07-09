package aula09.Exercicio9_1.Exercício6_1;

import java.time.LocalDate;
import java.util.Date;

import aula05.DateYMD;

public class Aluno extends Pessoa {

    private static int NMec=100;
    private DateYMD DataInsc;
    private LocalDate Insc = LocalDate.now();
    private int id;
    
    public Aluno(String nome, int cc, DateYMD dataNasc) {
        super(nome, cc, dataNasc);
    }
    public Aluno(String nome, int cc, DateYMD dataNasc, DateYMD DataInsc) {
        super(nome, cc, dataNasc);
        this.DataInsc = DataInsc;
    }
    public DateYMD getDataInsc(){
        return DataInsc;
    }

    public DateYMD DataInsc(){
        DateYMD f = new DateYMD(Insc.getDayOfMonth(), Insc.getMonthValue(), Insc.getYear());
        return f;
    } 
      public int getNMec() {
        this.id = ++Aluno.NMec;
        return id;
    }

    @Override
    public String toString() {
        return "Número Mecanográfico: " + getNMec() + "; Data de inscrição: " + DataInsc.getDay()+"/"+DataInsc.getMonth()+"/"+DataInsc.getYear();
    }
    public void setNMec(int nMec) {
        NMec++;
    }
    public void setDataInsc(DateYMD dataInsc) {
        DataInsc = dataInsc;
    }
    public void setInsc(LocalDate insc) {
        Insc = insc;
    }
}
