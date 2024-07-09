import java.util.List;


public class SharkCompany2 {

	public static void main(String[] args) {
		Person2[] persons = { new Person2("Maria Silva"),
				new Person2("Manuel Pereira"),
				new Person2("Aurora Machado"),
				new Person2("Augusto Lima")
		};
		Company2 shark = new Company2();
		Company2.user = User2.COMPANY;
		shark.admitEmployee(persons[0], 1000);
		shark.admitEmployee(persons[1], 900);
		shark.admitEmployee(persons[2], 1200);
		shark.admitEmployee(persons[3], 1100);
		List<Employee2> sharkEmps = shark.employees();
		for (Employee2 e : sharkEmps)
			System.out.println(e.getSalary());
		shark.paySalaries(1);
	}
}