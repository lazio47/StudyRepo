package aula02;

import java.util.Scanner;
public class Exercicio8 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        double A, B;
        do{
            System.out.println("Introduza o valor do cateto A: ");
            A = sc.nextDouble();
            System.out.println("Introduza o valor do cateto B: ");
            B = sc.nextDouble();
            
        }while ((A < 0) || (B < 0));
        double C = Math.sqrt(Math.pow(A, 2) + Math.pow(B, 2));
        double ANG = Math.acos(A/C);
        System.out.println("O valor da hipotenusa é de "+C+" e o ângulo entre A e C é de "+ANG+" rad.");
        sc.close();
    }
    
}
