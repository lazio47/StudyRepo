
class BankAccountImpl3 implements BankAccount3 {
	
private String bank;
private double balance;

	BankAccountImpl3(String bank, double initialDeposit) {
		this.bank = bank;
		balance = initialDeposit;
	}
	
	public String getBank() {
		return bank;	
	}
	@Override public void deposit(double amount) {
			balance += amount;
	}
	@Override public boolean withdraw(double amount) {
		if (amount > balance )
			return false;
		balance -= amount;
		return true;
	}
	@Override public double balance() {
		return balance;
	}
}