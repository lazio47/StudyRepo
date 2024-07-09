package aula07.Exercicio7_2;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        int  option, day, month, year;
        Scanner sc = new Scanner(System.in);
        DateYMD date = new DateYMD(12, 6, 2018);

    
        do{
            System.out.println("Date operations: ");
            System.out.println("1 - create new date");
            System.out.println("2 - show current date");
            System.out.println("3 - incremet date");
            System.out.println("4 - decrement date");
            System.out.println("0 - exit");
            option = sc.nextInt();
            if (option==1){
                System.out.println("Day: ");
                day = sc.nextInt();
                System.out.println("Month: ");
                month = sc.nextInt();
                System.out.println("Year: ");
                year = sc.nextInt();
                date = new DateYMD(day, month, year);
            }else if(option==2){
                System.out.print(date);
            }else if(option==3){
                date.Increment(6);
            }else if(option==4){
                date.Decrement(8);
            }
        }while(option!=0);
        sc.close();
    }
}
