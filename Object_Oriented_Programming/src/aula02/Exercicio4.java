package aula02;

import java.util.Scanner;
public class Exercicio4 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Montante investido: ");
        double M = sc.nextDouble();
        System.out.println("Taxa de juro mensal: ");
        double T = sc.nextDouble();
        double M1 = M + T*M/100;
        double M2 = M1 + T*M1/100;
        double M3 = M2 + T*M2/100;
        System.out.println("Montante ao fim de 3 meses será de "+M3+" euros.");
        sc.close();
    }
}
