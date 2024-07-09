package aula08.Exercicio8_2;

import java.util.ArrayList;

public class Prato {
    protected String nome;
    protected ArrayList<Alimento> alimentos = new ArrayList<Alimento>();

    public Prato(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public boolean addIngrediente(Alimento a){
        if (this instanceof PratoVegetariano){
            if(a instanceof Legume||a instanceof Cereal){
                alimentos.add((AlimentoVegetariano)a);
                return true;
            }
        }else if (this instanceof PratoDieta){
            if(a.calorias<100){
                alimentos.add(a);
                return true;
            }
        }else{
            alimentos.add(a);
            return true;
        }
        
        return false;
    }

    @Override
    public String toString() {
        return "Prato '"+getNome()+"', composto por "+alimentos.size()+" Ingredientes";
    }
    
}
