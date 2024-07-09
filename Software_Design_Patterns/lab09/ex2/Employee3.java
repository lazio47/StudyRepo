
class Employee3 {

private double salary;
private Person3 person;
	
	public Employee3(Person3 person, double s) {
		this.person = person;
		this.salary = s;
	}

	public double getSalary() {
		return salary;
	}

	public Person3 getPerson() {
		return person;
	}
}