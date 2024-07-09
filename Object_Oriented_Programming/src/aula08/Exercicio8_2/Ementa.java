package aula08.Exercicio8_2;

import java.util.ArrayList;

public class Ementa {

    private String nome;
    private String local;
    private ArrayList<Prato> pratos = new ArrayList<Prato>();
    public Ementa(String nome, String local) {
        this.nome = nome;
        this.local = local;
        //this.pratos = pratos;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getLocal() {
        return local;
    }
    public void setLocal(String local) {
        this.local = local;
    }
    public ArrayList<Prato> getPratos() {
        return pratos;
    }
    public void setPratos(ArrayList<Prato> pratos) {
        this.pratos = pratos;
    }

    public void addPrato(Prato prato, DiaSemana d){
            this.pratos.add(prato);
    }

    public void printEmenta(){
        for(int i=0; i<getPratos().size(); i++){
            
            if(pratos.get(i) instanceof PratoVegetariano){
                System.out.println("Prato '"+getPratos().get(i).getNome()+"', composto por "+pratos.get(i).alimentos.size()+" Ingredientes - Prato Vegetariano, dia "+DiaSemana.values()[i]);
            }else if (pratos.get(i) instanceof PratoDieta){
                System.out.println("Prato '"+getPratos().get(i).getNome()+"', composto por "+pratos.get(i).alimentos.size()+" Ingredientes - Prato Dieta ("+((PratoDieta)pratos.get(i)).getCalorias()+" Calorias ) , dia "+DiaSemana.values()[i]);
            }else{
                System.out.println("Prato '"+getPratos().get(i).getNome()+"', composto por "+pratos.get(i).alimentos.size()+" Ingredientes, dia "+DiaSemana.values()[i]);
            }
        }
    }
    @Override
    public String toString() {
        printEmenta();
        return "";
    }
    
}
