package aula02;

import java.util.Scanner;
public class Exercicio9 {
    public static void main(String[] args){
        int N;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("Introduza um valor positivo: ");
            N = sc.nextInt();
        }while(N < 0);
        for (int i = N; i >= 0; i--) {
            if(i%10==0){
                System.out.println(i+", ");
            }else{
            System.out.print(i+", ");
            }
        }

        sc.close();
    }
}
