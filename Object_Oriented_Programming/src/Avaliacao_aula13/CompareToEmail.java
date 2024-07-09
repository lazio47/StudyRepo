package Avaliacao_aula13;

import java.util.Comparator;

public class CompareToEmail implements Comparator<Contact>{
    @Override 
    public int compare(Contact m1, Contact m2){
        
        return m1.getEmail().compareTo(m2.getEmail());
    }
}
