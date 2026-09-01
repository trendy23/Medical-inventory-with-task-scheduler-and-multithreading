
import java.util.ArrayList;
import java.util.List;

// ==========================================
// 1. ABSTRACTION & BASE CLASS
// ==========================================
abstract class BankAccount {
    private String owner;
    private String accountNumber;
    
    // 2. ENCAPSULATION
    // Private field prevents direct external tampering
    private double balance;

    public BankAccount(String owner, String accountNumber, double initialBalance) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.balance = Math.max(0.0, initialBalance);
    }

    // Controlled getter for encapsulated balance
    public double getBalance() {
        return this.balance;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.printf("[%s] Deposited $%.2f. New Balance: $%.2f%n", 
                accountNumber, amount, balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            System.out.printf("[%s] Withdrew $%.2f. Remaining Balance: $%.2f%n", 
                accountNumber, amount, balance);
            return true;
        }
        System.out.printf("[%s] Withdrawal failed: Insufficient funds.%n", accountNumber);
        return false;
    }

    // Abstract method: Subclasses MUST define their own behavior
    public abstract void applyMonthlyProcess();
}

// ==========================================
// 3. INHERITANCE: SavingsAccount
// ==========================================
class SavingsAccount extends BankAccount {
    private double interestRate;

    public SavingsAccount(String owner, String accountNumber, double balance, double interestRate) {
        super(owner, accountNumber, balance);
        this.interestRate = interestRate;
    }

    // 4. POLYMORPHISM
    @Override
    public void applyMonthlyProcess() {
        double interest = getBalance() * interestRate;
        deposit(interest);
        System.out.printf("[%s] Applied interest of $%.2f%n", getAccountNumber(), interest);
    }
}

// ==========================================
// 3. INHERITANCE: CheckingAccount
// ==========================================
class CheckingAccount extends BankAccount {
    private double monthlyFee;

    public CheckingAccount(String owner, String accountNumber, double balance, double monthlyFee) {
        super(owner, accountNumber, balance);
        this.monthlyFee = monthlyFee;
    }

    // 4. POLYMORPHISM
    @Override
    public void applyMonthlyProcess() {
        System.out.printf("[%s] Charging monthly maintenance fee of $%.2f%n", 
            getAccountNumber(), monthlyFee);
        withdraw(monthlyFee);
    }
}

// ==========================================
// MAIN DRIVER CLASS
// ==========================================
public class Main {
    public static void main(String[] args) {
        // Create accounts
        SavingsAccount aliceSavings = new SavingsAccount("Alice", "SAV-101", 1000.0, 0.04);
        CheckingAccount bobChecking = new CheckingAccount("Bob", "CHK-202", 500.0, 12.00);

        System.out.println("--- 1. ENCAPSULATION IN ACTION ---");
        // Direct mutation is impossible: bobChecking.balance = 1000000; (Compiler Error)
        bobChecking.deposit(150.0);
        System.out.printf("Bob's current balance: $%.2f%n%n", bobChecking.getBalance());

        System.out.println("--- 2. POLYMORPHISM & ABSTRACTION IN ACTION ---");
        // Treating diverse derived classes uniformly via the abstract parent type
        List<BankAccount> portfolio = new ArrayList<>();
        portfolio.add(aliceSavings);
        portfolio.add(bobChecking);

        System.out.println("Executing monthly processing across portfolio...");
        for (BankAccount account : portfolio) {
            account.applyMonthlyProcess(); // Triggers custom subclass behavior dynamically
        }
    }
}