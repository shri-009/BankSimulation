import java.util.ArrayList;
import java.util.List;

public class BankSimulation {

    static class Account {
        private String owner;
        private double balance;
        private List<String> history;

        public Account(String owner, double initialBalance) {
            this.owner = owner;
            this.balance = initialBalance;
            this.history = new ArrayList<>();
            history.add(String.format("Account created with initial balance: %.2f", balance));
        }

        public void deposit(double amount) {
            if (amount <= 0) {
                history.add(String.format("Failed deposit: non-positive amount %.2f", amount));
                System.out.println("Deposit amount must be positive.");
                return;
            }
            balance += amount;
            history.add(String.format("Deposited: %.2f, New Balance: %.2f", amount, balance));
            System.out.printf("Deposited %.2f successfully.%n", amount);
        }

        public void withdraw(double amount) {
            if (amount <= 0) {
                history.add(String.format("Failed withdrawal: non-positive amount %.2f", amount));
                System.out.println("Withdrawal amount must be positive.");
                return;
            }
            if (amount > balance) {
                history.add(String.format("Failed withdrawal: %.2f (Insufficient funds)", amount));
                System.out.println("Insufficient funds.");
            } else {
                balance -= amount;
                history.add(String.format("Withdrew: %.2f, New Balance: %.2f", amount, balance));
                System.out.printf("Withdrew %.2f successfully.%n", amount);
            }
        }

        public double getBalance() {
            return balance;
        }

        public void printHistory() {
            System.out.println("\nTransaction History for " + owner + ":");
            for (String entry : history) {
                System.out.println(entry);
            }
        }
    }

    public static void main(String[] args) {
        Account acct = new Account("Alice", 1000.00);
        acct.deposit(200.00);
        acct.withdraw(150.00);
        acct.withdraw(2000.00);  // Attempt to overdraw
        acct.deposit(-50.00);    // Invalid deposit
        acct.withdraw(-30.00);   // Invalid withdrawal

        System.out.printf("%nCurrent Balance: %.2f%n", acct.getBalance());
        acct.printHistory();
    }
}
