package aula12.Exercicio12_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class OrdenarPalavras {
    public static void main(String[] args) throws FileNotFoundException {
        Map<Character, TreeMap<String, Integer>> Palavras = new TreeMap<Character, TreeMap<String, Integer>>();
        try{
            Scanner input = new Scanner(new FileReader("A_cidade_e_as_serras.txt"));
            while(input.hasNext()){
                String[] novasPalavras = input.next().split("[\t\n.,:?!=|\\(\\)\\[\\]\\{\\}/]");
                for(String s : novasPalavras){
                    if(s.length()>2&&s.matches("[a-zA-Z]+")){
                        if(Palavras.containsKey(s.toLowerCase().charAt(0))){
                            if(Palavras.get(s.toLowerCase().charAt(0)).containsKey(s.toLowerCase())){
                                Palavras.get(s.toLowerCase().charAt(0)).replace(s.toLowerCase(), Palavras.get(s.toLowerCase().charAt(0)).get(s.toLowerCase())+1);
                            }else{
                                Palavras.get(s.toLowerCase().charAt(0)).put(s.toLowerCase(), 1);
                            }
                        }else{
                            TreeMap<String, Integer> umaPalavra = new TreeMap<String, Integer>();
                            umaPalavra.put(s.toLowerCase(), 1);
                            Palavras.put(s.toLowerCase().charAt(0), umaPalavra);
                        }
                    }
                }
            }
            
        }catch(IOException e){
            System.out.println("File not found.!");
        }

        File file = new File("Palavras_ordenadas.txt");
        PrintWriter writer = new PrintWriter(file);
            for (java.util.Map.Entry<Character, TreeMap<String, Integer>> set : Palavras.entrySet()) {
                System.out.print(set.getKey()+": ");
                writer.print(set.getKey()+": ");
                for (java.util.Map.Entry<String, Integer> set1 : set.getValue().entrySet()) {
                    System.out.print(set1.getKey()+", "+set1.getValue()+"; ");
                    writer.print(set1.getKey()+", "+set1.getValue()+"; ");
                }
                System.out.println("");
                writer.println("");
            }
        writer.close();
        
    }
}
