package aula02;

import java.util.Scanner;
public class Exercicio10 {
    public static void main(String[] args){
        double max, min, num, sum, prox;
        Scanner sc = new Scanner(System.in);
        System.out.println("Digita um número: ");
        Double N = sc.nextDouble();
        max = N;
        min = N;
        num = 1;
        sum = N;
        do{
            System.out.println("Digita um número: ");
            prox = sc.nextDouble();
            if(prox > max){
                max = prox;
            }else if(prox < min){
                min = prox;
            }
            num++;
            sum += prox;
        }while(N != prox);
        System.out.println("Máximo: "+max);
        System.out.println("Mínimo: "+min);
        System.out.println("Média: "+sum/num);
        System.out.println("Números digitados: "+num);
        sc.close();
    }
}
