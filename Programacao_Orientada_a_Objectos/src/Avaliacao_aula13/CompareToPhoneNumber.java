package Avaliacao_aula13;

import java.util.Comparator;

public class CompareToPhoneNumber implements Comparator<Contact>{
    @Override 
    public int compare(Contact m1, Contact m2){
        return Integer.toString(m1.getPhoneNumber()).compareTo(Integer.toString(m2.getPhoneNumber()));
    }
}
