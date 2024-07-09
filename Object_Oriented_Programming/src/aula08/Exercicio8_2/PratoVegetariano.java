package aula08.Exercicio8_2;

import java.util.ArrayList;

public class PratoVegetariano extends Prato implements Comparable<PratoVegetariano> {
    
    private String nome;
    //private ArrayList<AlimentoVegetariano> alimentos = new ArrayList<AlimentoVegetariano>();
    private int calorias;

    public PratoVegetariano(String nome) {
        super(nome);
        //this.alimentos = alimentos;
    }
    
    public ArrayList<Alimento> getAlimentos() {
        return alimentos;
    }
    public void setAlimentos(ArrayList<Alimento> alimentos) {
        this.alimentos = alimentos;
    }

    public int getCalorias(){
        calorias = 0;
        for(int i=0; i<alimentos.size();i++){
            calorias+=alimentos.get(i).getCalorias();
        }
        return calorias;
    }

    public double getPeso(){
        int peso = 0;
        for(int i=0; i<alimentos.size();i++){
            peso+=alimentos.get(i).getPeso();
        }
        return peso;
    }

    public double getProteina(){
        int proteina = 0;
        for(int i=0; i<alimentos.size();i++){
            proteina+=alimentos.get(i).getProteinas();
        }
        return proteina;
    }


    @Override
    public String toString() {
        return "Prato '"+getNome()+"', composto por "+alimentos.size()+" Ingredientes - Prato Vegetariano";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((alimentos == null) ? 0 : alimentos.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PratoVegetariano other = (PratoVegetariano) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (alimentos == null) {
            if (other.alimentos != null)
                return false;
        } else if (!alimentos.equals(other.alimentos))
            return false;
        return true;
    }

    @Override
    public int compareTo(PratoVegetariano o) {
        if(this.getCalorias()>o.getCalorias()){
            return 1;
        }else if(this.getCalorias()<o.getCalorias()){
            return -1;
        }else{
            return 0;
        }
    }

    
}
