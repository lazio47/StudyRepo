package aula10.Exercicio10_4;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws IOException{
        Scanner input = new Scanner(new FileReader("words.txt"));
        ArrayList<String> palavras = new ArrayList<String>();
        while (input.hasNext()) {
        String word = input.next();
        System.out.println(word);
        if(word.length()>2){
            palavras.add(word);
        }
        }

        System.out.println("-------------------------------------------------");
        System.out.println("Terminadas em s: ");
        System.out.println("-------------------------------------------------");
        for(String s: palavras){
            if(s.endsWith("s")){
                System.out.println(s);
            }
        }

        for(int i=0; i < palavras.size(); i++){
            if(!check(palavras.get(i))){
                palavras.remove(i);
            }
        }
        System.out.println("-------------------------------------------------");
        System.out.println("Apenas com letras: ");
        System.out.println("-------------------------------------------------");
        for(String s: palavras){
            System.out.println(s);  
        }
    }
    public static boolean check(String s){
            if (s == null){
               return false;
            }
            int len = s.length();
            for (int i = 0; i < len; i++) {
               if ((Character.isLetter(s.charAt(i)) == false)) {
                  return false;
               }
            }
            return true;
    }
        
}
