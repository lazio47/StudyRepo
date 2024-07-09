package aula09.Exercicio9_1.Exercicio7_2;

public abstract class Date {
    public abstract void Increment(int increment);

    public abstract void Decrement(int decrement);

    public static boolean ValidDate(int day, int month, int year) {
        if (ValidMonth(month) && day < GetDays(month, year) && year > 0)
            return true;
        else
            return false;
    }

    public static boolean ValidMonth(int month) {
        if (month <= 0 || month > 12)
            return true; // return is swapped because of the do while in DateMain
        else
            return false;
    }

    public static int GetDays(int month, int year) {
        int days = 0;
        switch (month) {
            case 1, 3, 5, 8, 7, 10, 12:
                days = 31;
                break;
            case 4, 6, 9, 11:
                days = 30;
                break;
            case 2:
                if (LeapYear(year)) {
                    days = 29;
                } else
                    days = 28;
                break;
        }
        return days;
    }

    public static boolean LeapYear(int year) {
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
            return true;
        else
            return false;
    }
}
