package aula05;

import java.util.Scanner;

public class DateYMD {
    int day, month, year;

    public DateYMD(int day, int month, int year) {
        if(valid(day, month, year)){
        this.day = day;
        this.month = month;
        this.year = year;
        }
    }
    public DateYMD() {
        assert valid(day, month, year);
    }
    public static boolean validMonth(int month){
        if(month>=1&&month<=12){
            return true;
        }else{return false;}
    }
    public static int monthDays(int month, int year){
        switch(month){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 2:
                if(leapYear(year)){
                    return 29;
                }else{return 28;}
            default:
                return 30;
        }
    }
    public static boolean leapYear(int year){
        if((year>=1600)&&(year%4==0)&&(year%100!=0)||(year%400==0)){
            return true;
        }else{return false;}
    }
    public static boolean valid(int day, int month, int year){
        if(validMonth(month)&&day>=1&&day<=monthDays(month, year)){
            return true;
        }
        return false;
    }
    public int getDay() {
        return day;
    }
    public int getMonth() {
        return month;
    }
    public int getYear() {
        return year;
    }

    public void set(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public void increment(){
        if((month==12)&&(day==monthDays(month, year))){
            day = 1;
            year ++;
            month = 1;
        }else if(day == monthDays(month, year)){
            month++;
            day = 1;
        }else{day++;}
    }

    public void decrement(){
        if((month==1)&&(day==1)){
            day = 31;
            year --;
            month = 12;
        }else if(day == 1){
            month--;
            day = monthDays(month, year);
        }else{day--;}
    }
    
    @Override
    public String toString() {
        if(valid(day, month, year)){
            return year+"-"+month+"-"+day+"\n";
        }
        return "No valid date\n";
    }
    
    public static void main(String[] args) {
        int  option, day, month, year;
        Scanner sc = new Scanner(System.in);
        DateYMD date = new DateYMD();
    
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
                date.increment();
            }else if(option==4){
                date.decrement();
            }
        }while(option!=0);
        sc.close();
    }
}

