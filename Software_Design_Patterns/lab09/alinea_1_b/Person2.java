
class Person2 {

	private String name;
	private BankAccount2 bankAccount;

	public Person2(String n) {
		name = n;
		bankAccount = new Proxy2(new BankAccountImpl2("PeDeMeia", 0));
	}

	public String getName() {
		return name;
	}

	public BankAccount2 getBankAccount() {
		return bankAccount;
	}
}