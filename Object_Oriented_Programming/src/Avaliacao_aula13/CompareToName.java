package Avaliacao_aula13;

import java.util.Comparator;

public class CompareToName implements Comparator<Contact> {
    @Override 
    public int compare(Contact m1, Contact m2){
        
        return m1.getName().compareTo(m2.getName());
    }
}
