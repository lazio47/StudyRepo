package aula10.Exercicio10_3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class IdentificadorCaractere {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<Character, ArrayList<Integer>> result;
        System.out.println("Introduza uma String: ");
        String palavra = sc.nextLine();
        result = putChar(palavra);
        System.out.println(result);
        sc.close();
    }

    public static Map<Character, ArrayList<Integer>> putChar(String s){
        Map<Character, ArrayList<Integer>> caracteres = new HashMap<Character, ArrayList<Integer>>();
        for (int i = 0; i < s.length(); i++) {
            if (caracteres.containsKey(s.charAt(i))){
                caracteres.get(s.charAt(i)).add(i);
            }else{
                caracteres.put(s.charAt(i), new ArrayList<Integer>());
                caracteres.get(s.charAt(i)).add(i);
            }
        }
        return caracteres;
    } 
}
