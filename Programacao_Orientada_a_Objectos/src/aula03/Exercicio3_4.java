package aula03;

import java.util.Scanner;
import java.lang.Math;
import java.text.DecimalFormat;

public class Exercicio3_4 {
    static int notaFinal(double n1, double n2){
        int num = 66;
        double nota = 0.4*n1+0.6*n2;
        if(n1<7||n2<7){
            return num;
        }else{
            return (int)nota;
        }
    }
    public static void main(String[] args){
        double notaP, notaT, notaT1, notaP1;
        int N, i;
        Scanner sc = new Scanner(System.in);
        int min = 0;  
        int max = 20;  
        System.out.println("Qual é o número de estudantes? ");
        N = sc.nextInt();

        double alunos[][] = new double[N][3];

        for (i=0; i<N; i++) {
            notaT = Math.random()*(max-min+1)+min;
            notaT1 = Math.round(notaT*10);
            alunos[i][0] = notaT1/10;
            notaP = Math.random()*(max-min+1)+min;
            notaP1 = Math.round(notaP*10);
            alunos[i][1] = notaP1/10;
            alunos[i][2] = notaFinal(notaT, notaP);
        }
        System.out.println("NotaT \t" + "NotaP \t" +"Pauta ");
        for (i=0; i<N; i++) {
            System.out.println(alunos[i][0] +"\t"+ +alunos[i][1]+" \t"+alunos[i][2]);
          }
        sc.close();
    }
}
