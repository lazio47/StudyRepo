package aula05;

import java.util.ArrayList;
import java.util.Arrays;
import aula05.imprimirMes;
import aula05.DateYMD;
import java.util.Scanner;


public class Exercicio_5_2 {
    static ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>();
    int month, year, firstWeekdayOfYear;
    DateYMD DateYMD = new DateYMD();
    imprimirMes imprimir = new imprimirMes(month, year, firstWeekdayOfYear);

    public Exercicio_5_2(int year, int firstWeekdayOfYear) {
        assert firstWeekdayOfYear<=7&&firstWeekdayOfYear>=1;
        this.year = year;
        this.firstWeekdayOfYear = firstWeekdayOfYear;
    }

    public int getYear() {
        return year;
    }

    public int getFirstWeekdayOfYear() {
        return firstWeekdayOfYear;
    }

    public static int firstWeekdayOfMonth(int month){
        int cont;
        if(month==1){
            return 1;
        }else{
        cont = imprimirMes.espaco;}
        return cont+2;
    }
    public static void addEvent(int day, int mes, int ano){
        ArrayList<Integer> fila = new ArrayList<Integer>();
        fila.add(day);
        fila.add(mes);
        fila.add(ano);
        arr.add(fila);
    }
    public static void removeEvent(int day, int mes, int ano){
        ArrayList<Integer> fila = new ArrayList<Integer>();
        fila.add(day);
        fila.add(mes);
        fila.add(ano);
        arr.removeIf(lista -> lista.equals(fila));
    }
    
   
    public static void printMonth(int month, int year){
        
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DateYMD novaData = new DateYMD();
        int resp;
        do{
        System.out.println("Calendar operations: ");
        System.out.println("1 - create new calendar");
        System.out.println("2 - print calendar month");
        System.out.println("3 - print calendar");
        System.out.println("4 - Adicionar Evento: ");
        System.out.println("5 - Remover Evento");
        System.out.println("0 - exit");
        resp = sc.nextInt();
        if(resp==3){
        for(int i=1; i<=12;i++){
                imprimirMes.imprimir(i, novaData.year, imprimirMes.dias(i, novaData.year), firstWeekdayOfMonth(i));
        }}else if(resp==2){
            System.out.println("Mes: ");
            int mes = sc.nextInt();
            imprimirMes.imprimir(mes, novaData.year, imprimirMes.dias(mes, novaData.year), mes);
            
        }else if(resp==1){
            System.out.println("Primeiro dia da semana do ano: ");
            int dia = sc.nextInt();
            System.out.println("Ano: ");
            int ano = sc.nextInt();
            novaData = new DateYMD(dia, 1, ano);
        }else if(resp==4){
            System.out.println("dia: ");
            int dia = sc.nextInt();
            System.out.println("Mes: ");
            int mes = sc.nextInt();
            System.out.println("ano: ");
            int ano = sc.nextInt();
            addEvent(dia, mes, ano);
            
        }else if(resp==5){
            System.out.println("dia: ");
            int dia = sc.nextInt();
            System.out.println("Mes: ");
            int mes = sc.nextInt();
            System.out.println("ano: ");
            int ano = sc.nextInt();
            removeEvent(dia, mes, ano);
            
        }
        }while(resp!=0);
        System.out.println(arr);
        sc.close();
    }
    
}
