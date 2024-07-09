package aula02;

import java.util.Scanner;
public class Exercicio2 {
    public static void main(String[] args){
        System.out.println("Introduza a temperatura em graus Celsius: ");
        Scanner sc = new Scanner(System.in);
        double t = sc.nextDouble();
        double tf = 1.8*t + 32;
        System.out.println("Temperatura em graus Fahrenheit: "+ tf);
        sc.close();
    }
}
