public class Proxy3 implements BankAccount3 {

    private BankAccount3 bankAccount;

    public Proxy3(BankAccount3 bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void deposit(double amount) {
        bankAccount.deposit(amount);
    }

    public boolean withdraw(double amount) {
        if (Company3.user == User3.OWNER)
            return bankAccount.withdraw(amount);

        return false;
    }

    public double balance() {
        if (Company3.user == User3.OWNER)
            return bankAccount.balance();

        return 0;
    }
}
