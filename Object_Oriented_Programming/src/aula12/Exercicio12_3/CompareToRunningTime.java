package aula12.Exercicio12_3;

import java.util.Comparator;

public class CompareToRunningTime implements Comparator<Movie>{
    @Override 
    public int compare(Movie m1, Movie m2){
        
        return m1.getRunningTime().compareTo(m2.getRunningTime());
    }
}
