package aula03;

import java.util.Scanner;
import java.util.Random;

public class Exercicio3_3 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        int N, Nj, i;
        String resp;
        N = random.nextInt(99) + 1;
        i =0;
        do{
            System.out.println("Tente adivinhar o número: ");
            Nj = sc.nextInt();
            i += 1;
            if(N == Nj){
                resp = "N";
                
            }else{
                if(Nj>N){
                    System.out.println("Demasiado Alto.");
                }else{
                    System.out.println("Demasiado baixo.");
                }
                System.out.println("Pretende continuar? Prima (S)im");
                resp = sc.next();
        }
        }while("S".equals(resp)||"Sim".equals(resp));
        if(N==Nj){
            System.out.println("Venceste. Precisaste de "+i+" jogadas. O número é "+N+".");
        }
        sc.close();
    }
}
