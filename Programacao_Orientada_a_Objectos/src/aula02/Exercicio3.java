package aula02;

import java.util.Scanner;
public class Exercicio3 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduza a quuantidade de água[kg]: ");
        double M = sc.nextDouble();
        System.out.println("Introduza a temperatura inicial[C]: ");
        double Ti = sc.nextDouble();
        System.out.println("Introduza a temperatura final[C]: ");
        double Tf = sc.nextDouble();
        double Q = M*(Tf-Ti)*4184;
        System.out.println("A energia necessária é de "+Q+"J.");
        sc.close();
    }
}
