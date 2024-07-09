public class Proxy implements BankAccount {

    private BankAccount bankAccount;

    public Proxy(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void deposit(double amount) {
        bankAccount.deposit(amount);
    }

    public boolean withdraw(double amount) {
        if (Company.user == User.OWNER)
            return bankAccount.withdraw(amount);

        return false;
    }

    public double balance() {
        if (Company.user == User.OWNER)
            return bankAccount.balance();

        return 0;
    }
}
