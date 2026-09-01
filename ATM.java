
import java.util.Scanner; 

 public class ATM {
static Scanner scanner = new Scanner(System.in);

     static double balance = 1000.00;
     static double recipientBalance = 500.00;

     public static void main(String[] args) {

       int choice;

        System.out.println("================================");
        System.out.println("       WELCOME TO SIMPLE ATM");
        System.out.println("================================");
        do {
            System.out.println();
            System.out.println("ATM MENU");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Transfer Money");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    checkBalance();
                    break;

                case 2:
                    depositMoney();
                    break;

                case 3:
                    withdrawMoney();
                    break;

                case 4:
                    transferMoney();
                    break;

                case 5:
                    System.out.println("Thank you for using the ATM!");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }

        } while (choice != 5);

        scanner.close();
    }

    // Check account balance
    public static void checkBalance() {
        System.out.printf("Your current balance is: $%.2f%n", balance);
    }

    // Deposit money
    public static void depositMoney() {

        System.out.print("Enter amount to deposit: $");
        double amount = scanner.nextDouble();

        if (amount > 0) {
            balance = balance + amount;

            System.out.printf(
                "$%.2f deposited successfully.%n",
                amount
            );

            checkBalance();

        } else {
            System.out.println(
                "Deposit amount must be greater than zero."
            );
        }
    }

    // Withdraw money
    public static void withdrawMoney() {

        System.out.print("Enter amount to withdraw: $");
        double amount = scanner.nextDouble();

        if (amount <= 0) {

            System.out.println(
                "Withdrawal amount must be greater than zero."
            );

        } else if (amount > balance) {

            System.out.println("Insufficient balance.");

        } else {

            balance = balance - amount;

            System.out.printf(
                "$%.2f withdrawn successfully.%n",
                amount
            );

            checkBalance();
        }
    }

    // Transfer money
    public static void transferMoney() {

        System.out.print("Enter amount to transfer: $");
        double amount = scanner.nextDouble();

        if (amount <= 0) {

            System.out.println(
                "Transfer amount must be greater than zero."
            );

        } else if (amount > balance) {

            System.out.println(
                "Insufficient balance for this transfer."
            );

        } else {

            balance = balance - amount;
            recipientBalance = recipientBalance + amount;

            System.out.printf(
                "$%.2f transferred successfully.%n",
                amount
            );

            checkBalance();
        }
    }
}


