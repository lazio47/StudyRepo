package aula03;

import java.util.Scanner;
public class Exercicio3_1 {
    static boolean IsPrime(int i){
        int cont, j; 
        cont = 0;
            for(j=1; j<=i; j++){
                if(i%j == 0){
                    cont++;
                }
            }
            if(cont ==2){
                return true;
            }else{return false;}
    }
    public static void main(String[] args){
        int N, i, sum;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("Introduza um número: ");
            N = sc.nextInt();
        }while(N<=0);
        sum = 0;
        for(i=2; i<=N; i++){
            if(IsPrime(i)){
                sum += i;
            }
        }
        System.out.println("A soma de todos os números primos até "+N+" é "+sum+".");
        sc.close();
    }
}
