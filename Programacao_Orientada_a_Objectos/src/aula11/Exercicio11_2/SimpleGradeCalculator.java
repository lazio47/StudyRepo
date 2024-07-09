package aula11.Exercicio11_2;

import java.util.List;

public class SimpleGradeCalculator implements IGradeCalculator {

    @Override
    public double calculate(List<Double> grades) {
        int n = 1;
        Double notas = 0.0;
        for(int i= 0; i < grades.size(); i++){
            notas += grades.get(i);
            n = i+1;
        }
        return  notas/n;
    }
    
}
