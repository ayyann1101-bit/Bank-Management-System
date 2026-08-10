import java.util.*;

// Interface
interface BankOperations {
    void deposit(double amount);
    void withdraw(double amount);
    void checkBalance();
}

// Parent class
class Account {
    private int accNo;
    private String name;
    private String password;
    protected double balance;

    // Constructor
    Account(int accNo, String name, String password) {
        this.accNo = accNo;
        this.name = name;
        this.password = password;
        this.balance = 0;
    }

    // Getter
    public int getAccNo() {
        return accNo;
    }

    public String getName() {
        return name;
    }

    // Password check
    public boolean checkPassword(String pass) {
        return password.equals(pass);
    }
}

// Child class
class SavingsAccount extends Account implements BankOperations {

    SavingsAccount(int accNo, String name, String password) {
        super(accNo, name, password);
    }

    // Deposit
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid amount!");
        }
    }

    // Withdraw
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount!");
        } else if (amount > balance) {
            System.out.println("Insufficient balance!");
        } else {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        }
    }

    // Check Balance
    public void checkBalance() {
        System.out.println("Balance: " + balance);
    }
}

// Main class
public class Main {

    // Method to find account
    static SavingsAccount findAccount(ArrayList<SavingsAccount> list, int accNo) {
        for (SavingsAccount acc : list) {
            if (acc.getAccNo() == accNo) {
                return acc;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<SavingsAccount> accounts = new ArrayList<>();

        while (true) {
            System.out.println("\n==== BANK SYSTEM ====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            // Create Account
            if (choice == 1) {
                System.out.print("Enter Account No: ");
                int no = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter Name: ");
                String name = sc.nextLine();

                System.out.print("Set Password: ");
                String pass = sc.nextLine();

                if (findAccount(accounts, no) != null) {
                    System.out.println("Account already exists!");
                } else {
                    accounts.add(new SavingsAccount(no, name, pass));
                    System.out.println("Account Created!");
                }

            // Deposit
            } else if (choice == 2) {
                System.out.print("Enter Account No: ");
                int no = sc.nextInt();

                SavingsAccount acc = findAccount(accounts, no);

                if (acc == null) {
                    System.out.println("Account not found!");
                } else {
                    System.out.print("Enter Password: ");
                    String pass = sc.next();

                    if (acc.checkPassword(pass)) {
                        System.out.print("Enter Amount: ");
                        acc.deposit(sc.nextDouble());
                    } else {
                        System.out.println("Wrong password!");
                    }
                }

            // Withdraw
            } else if (choice == 3) {
                System.out.print("Enter Account No: ");
                int no = sc.nextInt();

                SavingsAccount acc = findAccount(accounts, no);

                if (acc == null) {
                    System.out.println("Account not found!");
                } else {
                    System.out.print("Enter Password: ");
                    String pass = sc.next();

                    if (acc.checkPassword(pass)) {
                        System.out.print("Enter Amount: ");
                        acc.withdraw(sc.nextDouble());
                    } else {
                        System.out.println("Wrong password!");
                    }
                }

            // Check Balance
            } else if (choice == 4) {
                System.out.print("Enter Account No: ");
                int no = sc.nextInt();

                SavingsAccount acc = findAccount(accounts, no);

                if (acc == null) {
                    System.out.println("Account not found!");
                } else {
                    System.out.print("Enter Password: ");
                    String pass = sc.next();

                    if (acc.checkPassword(pass)) {
                        acc.checkBalance();
                    } else {
                        System.out.println("Wrong password!");
                    }
                }

            // Exit
            } else if (choice == 5) {
                System.out.println("Thank you!");
                break;

            } else {
                System.out.println("Invalid choice!");
            }
        }
    }
}