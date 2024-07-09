
class Employee2 {

	private BankAccount2 bankAccount;

	private double salary;
	
	public Employee2(String n, double s) {
		salary = s;
		bankAccount = new Proxy2(new BankAccountImpl2("PeDeMeia", 0));
	}

	public double getSalary() {
		return salary;
	}

	public void depositSalary(double salary) {
		bankAccount.deposit(salary);
	}

	public void balance() {
		bankAccount.balance();
	}

}