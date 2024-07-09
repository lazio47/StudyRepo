package aula03;


import java.util.Scanner;;
public class Exercicio3_5 {
    static int[] valores(){
        int mm, yyyy, ds;
        int[] data1 = new int[3];
        do{
            Scanner sc = new Scanner(System.in);
            System.out.println("Introduza o mes: ");
            mm = sc.nextInt();
            System.out.println("Introduza o ano: ");
            yyyy = sc.nextInt();
            System.out.println("Dia da semana em que começa o mês: ");
            ds = sc.nextInt();
            data1[0]=ds;
            data1[1]=mm;
            data1[2]=yyyy;
        }while(mm<1||mm>12||ds<1||ds>7);
        return data1;
    }
    static int dias(int mes, int ano){
        int numdays = 30;
        switch (mes) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
              numdays = 31;
              break;
            case 2:
              numdays = 28;
              break;
            }
            if((ano%4==0 && ano%100!=0)||ano%400==0){
                numdays = 29;
            }
            return numdays;
    }
    static void imprimir(int mes, int ano, int numdays, int ds){
        System.out.println("\t"+mes+"/"+ano);
        System.out.println(" Su Mo Tu We Th Fr Sa");

        //print initial spaces
        String initialSpace = "";
        for (int i = 0; i < ds - 1; i++) {
            initialSpace += "   ";
        }
        System.out.print(initialSpace);

        for (int i = 0, diames = 1; diames <= numdays; i++) {
            for (int j = ((i == 0) ? ds - 1 : 0); j < 7 && (diames <= numdays); j++) {
                System.out.printf("%2d ", diames);
                diames++;
            }
            System.out.println();
        }

    }
    public static void main(String[] args){
        int[] valores = valores();
        int numdays = dias(valores[1], valores[2]);
        imprimir(valores[1], valores[2], numdays, valores[0]);
    }

}
