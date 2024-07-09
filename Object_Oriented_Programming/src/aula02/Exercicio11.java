package aula02;

import java.util.Scanner;
public class Exercicio11 {
    public static void Inteiro() {
        Scanner sc = new Scanner(System.in);
        int N;
        do{
        System.out.println("Introduza um valor no intervalo entre 0 a 10: ");
        N = sc.nextInt();
        }while(N<0 || N>10);
        sc.close();
      }
}
