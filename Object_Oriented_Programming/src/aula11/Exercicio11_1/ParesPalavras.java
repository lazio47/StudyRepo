package aula11.Exercicio11_1;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Scanner;

public class ParesPalavras {
    public static void main(String[] args) throws IOException{
        Scanner input = new Scanner(new FileReader("major.txt"));
        ArrayList<String> palavras = new ArrayList<String>();
        while (input.hasNext()) {
            String[] word = input.next().split("[\t\n.,:'‘’;?!-*\\{\\}=+&/\\(\\)\\[\\]”“\"\']");
            for(String s: word){
                if(s.length()>2&&s.matches("[a-zA-Z\\d]+")){
                    palavras.add(s);
                }
            }   
        }
        TreeMap<String, TreeMap<String, Integer>> Pares = new TreeMap<String, TreeMap<String, Integer>>();
        for(int i = 0; i<palavras.size()-1; i++){
            String p = palavras.get(i);
            String o = palavras.get(i+1);
            if(Pares.containsKey(p)){
                if(Pares.get(p).containsKey(o)){
                    Pares.get(p).replace(o, Pares.get(p).get(o)+1);
                }else{
                Pares.get(p).put(o, 1);}
            }else{
                TreeMap<String, Integer> SubPares = new TreeMap<String, Integer>();
                SubPares.put(o, 1);
                Pares.put(p, SubPares);
            }
        }

        for (java.util.Map.Entry<String, TreeMap<String, Integer>> set : Pares.entrySet()) {
            System.out.println(set.getKey()+"="+set.getValue());
        }
    }
}
