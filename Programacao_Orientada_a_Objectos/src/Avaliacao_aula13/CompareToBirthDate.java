package Avaliacao_aula13;

import java.util.Comparator;

public class CompareToBirthDate implements Comparator<Contact> {
    @Override 
    public int compare(Contact m1, Contact m2){
        
        return m1.getBirthDate().compareTo(m2.getBirthDate());
    }
}
