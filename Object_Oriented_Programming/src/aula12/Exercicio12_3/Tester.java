package aula12.Exercicio12_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class Tester {
    public static void main(String[] args) {
        List<Movie> movies = new ArrayList<Movie>();
        TreeMap<String, Integer> genre = new TreeMap<String, Integer>();
        try{
            Scanner input = new Scanner(new FileReader("movies.txt"));
            input.nextLine();
            while(input.hasNextLine()){
                String[] mov = input.nextLine().split("\t");
                movies.add(new Movie(mov[0], Double.parseDouble(mov[1]), mov[2], mov[3], Double.parseDouble(mov[4])));
            }
        }catch(IOException e){
            System.out.println("Ficheiro não encontrado!");
        }

        System.out.println(movies);
        Collections.sort(movies, new CompareToScore());
        System.out.println(" ");
        System.out.println(movies);
        Collections.sort(movies, new CompareToRunningTime());
        System.out.println(" ");
        System.out.println(movies);

        for(Movie m : movies){
            if(genre.containsKey(m.getGenre())){
                genre.replace(m.getGenre(), genre.get(m.getGenre())+1);
            }else{
                genre.put(m.getGenre(), 1);
            }
        }
        for (java.util.Map.Entry<String, Integer> set : genre.entrySet()) {
            System.out.println(set.getKey()+": "+set.getValue()+ "; ");
            
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Seleciona um genero: ");
        System.out.println("1. action ");
        System.out.println("2. comedy ");
        System.out.println("3. drama ");
        System.out.println("4. horror ");
        System.out.println("5. suspense");
        int escolha = sc.nextInt();
        String genero = "";
        switch(escolha){
            case 1:
                genero = "action";
                break;
            case 2:
                genero = "comedy";
                break;
            case 3:
                genero = "drama";
                break;
            case 4:
                genero = "horror";
                break;
            case 5:
                genero = "suspense";
                break;
            default:
                System.out.println("Escolha inválida!");
        }
        File file = new File("myselection.txt");
        try{
            PrintWriter writer = new PrintWriter(file);
            for(Movie m : movies){
                if(m.getScore()>60.0&&m.getGenre().equals(genero)){
                    System.out.println(m.getName()+"\t"+m.getScore()+"\t"+m.getRating()+"\t"+m.getGenre()+"\t"+m.getRunningTime());
                    writer.println(m.getName()+"\t"+m.getScore()+"\t"+m.getRating()+"\t"+m.getGenre()+"\t"+m.getRunningTime());
                }
            }
            writer.close();
            System.out.println("Ficheiro criado com sucesso!");
        }catch(FileNotFoundException e){
            System.out.println("Ficheiro de escrita não encontrado! ");
        }
        
        sc.close();


    }
}
