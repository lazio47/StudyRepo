package aula02;

import java.util.Scanner;
public class Exercicio6 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduza o tempo em segundos: ");
        int t = sc.nextInt();
        int h = ((t-t%60)/60);
        int h1 = (h-h%60)/60;
        int m = (h%60)*60+(t%60);
        int m1 = (m-m%60)/60;
        int s = m%60;
        System.out.println(h1+":"+m1+":"+s);
        sc.close();

    }
}