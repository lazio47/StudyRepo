package aula07.Exercicio7_2;

public class DateYMD extends Date {
    int day, month, year;

    public DateYMD(int day, int month, int year) {
        setYear(year);
        setMonth(month);
        setDay(day);
    }

    public void Increment(int inc){
        for (int i = 1; i <= inc; i++) {
            if (getDay() + 1 > GetDays(getMonth(), getYear()) && getMonth() == 12) {
                setYear(getYear()+1);
                setMonth(1);
                setDay(1);
                i++;
            } else if (getMonth() != 12 && getDay() + 1 > GetDays(getMonth(), getYear())) {
                setDay(1);
                setMonth(getMonth()+1);
                i++;
            } else
                setDay(getDay()+1);
        }
    }

    public void Decrement(int dec){
        for (int i = 1; i <= dec; i++) {
            if (getDay() - 1 <= 0 && getMonth() == 1) {
                setYear(getYear()-1);
                setMonth(12);
                setDay(GetDays(getMonth(), getYear()));
                i++;
            } else if (getMonth() != 1 && getDay() - 1 <= 0) {
                setDay(GetDays(getMonth() - 1, getYear()));
                setMonth(getMonth()-1);
                i++;
            } else
                setDay(getDay()-1);
        }
    }
    
    @Override
    public String toString() {
        if(ValidDate(day, month, year)){
            return year+"-"+month+"-"+day+"\n";
        }
        return "No valid date\n";
    }
    
}

