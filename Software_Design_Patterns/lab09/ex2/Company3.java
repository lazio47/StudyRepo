import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
class Company3 {

public static User3 user;
private List<Employee3> emps = new ArrayList<>();

	public void admitEmployee(Person3 person, double salary) {
		Employee3 e = new Employee3(person, salary);
		emps.add(e);
	}
	
	public void paySalaries(int month) {
		for (Employee3 e : emps) {
			Person3 person = e.getPerson();
			person.deposit(e.getSalary());
		}
	}
	
	public List<Employee3> employees() {
		return Collections.unmodifiableList(emps);
	}

	public double avgSalary() {
		double sum = 0;
		int count = 0;
		for (Employee3 e : emps) {
			sum += e.getSalary();
			count++;
		}
		return sum / count;
	}

	public Company3 getCompany() {
		return this;
	}
}