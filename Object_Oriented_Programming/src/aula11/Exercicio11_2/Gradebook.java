package aula11.Exercicio11_2;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class Gradebook {
    TreeMap<String, List<Double>> alunos = new TreeMap<String, List<Double>>();
    public void load(String file) throws IOException{
        Scanner input = new Scanner(new FileReader(file));
        while (input.hasNextLine()) {
            List<Double> notas = new ArrayList<Double>();
            String[] word = input.nextLine().split("\\|");
            notas.add((Double.parseDouble(word[1])));
            notas.add((Double.parseDouble(word[2])));
            notas.add((Double.parseDouble(word[3])));
            alunos.put(word[0], notas);
        }
    }

    public void removeStudent(String aluno){
        alunos.remove(aluno);
    }
    public void addStudent(Student student){
        alunos.put(student.getName(), student.getGrades());
    }
    
    public Student getStudent(String aluno){
        Student student = new Student(aluno, alunos.get(aluno));
        return student;
    }
    public Double calculateAverageGrade(String aluno){
        List<Double> nota = alunos.get(aluno);
        SimpleGradeCalculator calculadora = new SimpleGradeCalculator();
        return calculadora.calculate(nota);
    }
    public void printAllStudents(){
        System.out.println("-----------------------------------------------");
        System.out.printf("%-22S %7S %7S %7S \n", "Nome", "Nota1", "Nota2", "Nota3");
        System.out.println("-----------------------------------------------");
        for (java.util.Map.Entry<String, List<Double>> set : alunos.entrySet()) {
            System.out.printf("%-22s %7.2f %7.2f %7.2f \n",set.getKey(),+set.getValue().get(0), set.getValue().get(1), set.getValue().get(2));
        }
        System.out.println("-----------------------------------------------");
    }
}
