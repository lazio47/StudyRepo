package aula12.Exercicio12_3;

import java.util.Comparator;

public class CompareToScore implements Comparator<Movie>{

    @Override
    public int compare(Movie o1, Movie o2) {
        int resultado = o2.getScore().compareTo(o1.getScore());
        return resultado;
    }
    
}
