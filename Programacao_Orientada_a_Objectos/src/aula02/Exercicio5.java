package aula02;

import java.util.Scanner;
public class Exercicio5 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        double v1, v2, d1, d2;
        do{
            System.out.println("Introduza a v1: ");
            v1 = sc.nextDouble();
            System.out.println("Introduza a v2: ");
            v2 = sc.nextDouble();
            System.out.println("Introduza a d1: ");
            d1 = sc.nextDouble();
            System.out.println("Introduza a d2: ");
            d2 = sc.nextDouble();

        }while ((v1 < 0) || (v2 < 0) || (d1 < 0) || (d2 < 0));
        double Vm = (v1*v2*d1 + v1*v2*d2)/(d1*v2+d2*v1);
        System.out.println("A velocidade média final é de "+Vm);
        sc.close();
    }
}
