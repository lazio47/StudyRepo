
class Person3 {
	
private String name;
private Proxy3 bankAccount;

	public Person3(String n) {
		name = n;
		bankAccount = new Proxy3(new BankAccountImpl3("PeDeMeia", 0));
	}

	public String getName() {
		return name;
	}
	
	public void deposit(double amount) {
		bankAccount.deposit(amount);
	}

	public boolean withdraw(double amount) {
		return bankAccount.withdraw(amount);
	}

	public double balance() {
		return bankAccount.balance();
	}
}