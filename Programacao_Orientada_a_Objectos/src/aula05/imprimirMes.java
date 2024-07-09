package aula05;

import java.util.ArrayList;

public class imprimirMes {
    int mm, yyyy, ds;
    int[] data1 = new int[3];
    static int espaco, p;

    public imprimirMes(int mm, int yyyy, int ds) {
        this.mm = mm;
        this.yyyy = yyyy;
        this.ds = ds;
    }
    static int[] valores(int mm, int yyyy, int ds){
        int[] data1 = new int[3];
        data1[0]=ds;
        data1[1]=mm;
        data1[2]=yyyy;
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
        espaco = 0;
        System.out.println("\t"+mes+"/"+ano);
        System.out.println("  Su  Mo  Tu  We  Th  Fr  Sa ");

        //print initial spaces
        String initialSpace = "";
        for (int i = 0; i < ds - 1; i++) {
            initialSpace += "    ";
        }
        System.out.print(initialSpace);

        for (int i = 0, diames = 1; diames <= numdays; i++) {
            for (int j = ((i == 0) ? ds - 1 : 0); j < 7 && (diames <= numdays); j++) {
                ArrayList<Integer> fila = new ArrayList<Integer>();
                    fila.add(diames);
                    fila.add(mes);
                    fila.add(ano);
                if(Exercicio_5_2.arr.contains(fila)){
                    System.out.print("*");
                    System.out.printf("%2d",diames);
                }else{
                System.out.printf("%3d ", diames);
                }
                diames++;
                espaco=j;
            }
            System.out.println();
        }

    }

}
