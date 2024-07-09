import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Company2 {

	public static User2 user;
	private List<Employee2> emps = new ArrayList<>();

	public void admitPerson(String name, double salary) {
		Employee2 e = new Employee2(name, salary);
		emps.add(e);
	}

	public void paySalaries(double month) {
		for (Employee2 e : emps) {
			e.depositSalary(e.getSalary());
		}
	}

	public List<Employee2> employees() {
		return Collections.unmodifiableList(emps);
	}

	public void admitEmployee(Person2 p, double salary) {
		Employee2 e = new Employee2(p.getName(), salary);
		emps.add(e);
	}
}