import java.util.List;

public class SharkCompany3 {
    public static void main(String[] args) {
        Person3[] persons = { new Person3("Maria Silva"),
        new Person3("Manuel Pereira"),
        new Person3("Aurora Machado"),
        new Person3("Augusto Lima") };
        Facade shark = new Facade();
        Company3.user = User3.COMPANY;
        shark.admitEmployee(persons[0], 1000);
        shark.admitEmployee(persons[1], 900);
        shark.admitEmployee(persons[2], 1200);
        shark.admitEmployee(persons[3], 1100);
        List<Employee3> sharkEmps = shark.employees();
        for (Employee3 e : sharkEmps)
            System.out.println(e.getSalary());
            shark.paySalaries(1);
        }
    }