package aula12.Exercicio12_1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class PalavrasDiferentes {
    public static void main(String[] args) throws FileNotFoundException {
        int i = 0;
        Scanner input = new Scanner(new FileReader("words.txt"));
        Set<String> palavras = new HashSet<String>();
        while(input.hasNext()){
            String[] novasPalavras = input.next().split("[\t\n.,:?!=|\\(\\)\\[\\]\\{\\}/]");
            for(String s : novasPalavras){
                if(s.matches("[a-zA-Z]+")){
                    palavras.add(s);
                    i++;
                }
            }
        }
        System.out.println("Número Total de Palavras: "+i);
        System.out.println("Número de Diferentes Palavras: "+palavras.size());
        System.out.println(palavras);
    }
}
