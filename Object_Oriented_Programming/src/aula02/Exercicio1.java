package aula02;

import java.util.Scanner;
public class Exercicio1 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduza a distância em quilómetros: ");
        double d = sc.nextDouble();
        double d1 = d/1.609;
        System.out.println("A distância em milhas é " + d1);
        sc.close();

    }
}
