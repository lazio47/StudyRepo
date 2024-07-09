package aula03;

import java.util.Scanner;
public class Exercicio3_2 {
    public static void main(String[] args){
        int i;
        double taxa, montante;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("Introduza o montante investido: ");
        montante = sc.nextDouble();
        }while(montante<=0 || montante%1000!=0);
        do{
            System.out.println("Introduza a taxa de juro mensal (entre 0% e 5%): ");
        taxa = sc.nextDouble();
        }while(taxa<0 || taxa>5);
        for(i=1; i<=12; i++){
            montante+=taxa*montante/100;
            System.out.println("No "+i+"º mês o valor será de: "+montante);
        }
        sc.close();
    }
}
