public class Proxy2 implements BankAccount2 {

    private BankAccount2 bankAccount;

    public Proxy2(BankAccount2 bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void deposit(double amount) {
        bankAccount.deposit(amount);
    }

    public boolean withdraw(double amount) {
        if (Company2.user == User2.OWNER)
            return bankAccount.withdraw(amount);

        return false;
    }

    public double balance() {
        if (Company2.user == User2.OWNER)
            return bankAccount.balance();

        return 0;
    }
}
