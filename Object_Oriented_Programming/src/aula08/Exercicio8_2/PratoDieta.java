package aula08.Exercicio8_2;

import java.util.ArrayList;

public class PratoDieta extends Prato implements Comparable<PratoDieta> {
    private String nome;
    //private ArrayList<Alimento> alimentos = new ArrayList<Alimento>();
    private double maxCalorias;
    private int calorias;

    public PratoDieta(String nome, double maxCalorias) {
        super(nome);
        //this.alimentos = alimentos;
        this.maxCalorias = maxCalorias;
    }


    public ArrayList<Alimento> getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(Alimento alimentos) {
        this.alimentos.add(alimentos);
    }

    public double getMaxCalorias() {
        return maxCalorias;
    }

    public void setMaxCalorias(double maxCalorias) {
        this.maxCalorias = maxCalorias;
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
        return "Prato '"+getNome()+"', composto por "+alimentos.size()+" Ingredientes - Prato Dieta ("+getCalorias()+" Calorias )";
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
        PratoDieta other = (PratoDieta) obj;
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
    public int compareTo(PratoDieta o) {
        if(this.getCalorias()>o.getCalorias()){
            return 1;
        }else if(this.getCalorias()<o.getCalorias()){
            return -1;
        }else{
            return 0;
        }
    }
}
