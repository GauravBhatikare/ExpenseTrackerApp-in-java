import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

class Expense {
    private String description;
    private double amount;
    private Date date;

    public Expense(String description, double amount, Date date) {
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }
}

class ExpenseTracker {
    private ArrayList<Expense> expenses;
    private SimpleDateFormat dateFormat;

    public ExpenseTracker() {
        this.expenses = new ArrayList<>();
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    public void addExpense(String description, double amount, String dateString) {
        try {
            Date date = dateFormat.parse(dateString);
            Expense expense = new Expense(description, amount, date);
            expenses.add(expense);
            System.out.println("Expense added: " + description + " ($" + amount + ") on " + dateString);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use the format yyyy-MM-dd.");
        }
    }

    public void viewExpenses() {
        System.out.println("Expense List:");
        for (Expense expense : expenses) {
            String formattedDate = dateFormat.format(expense.getDate());
            System.out.println(
                    expense.getDescription() + " - $" + expense.getAmount() + " on " + formattedDate);
        }
    }
}

public class ExpenseTrackerApp {
    private static final int ADD_EXPENSE = 1;
    private static final int VIEW_EXPENSES = 2;
    private static final int EXIT = 3;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ExpenseTracker expenseTracker = new ExpenseTracker();

            while (true) {
                printMenu();
                int choice = getChoice(scanner);

                switch (choice) {
                    case ADD_EXPENSE:
                        addExpense(scanner, expenseTracker);
                        break;
                    case VIEW_EXPENSES:
                        expenseTracker.viewExpenses();
                        break;
                    case EXIT:
                        System.out.println("Exiting the program. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred. Exiting the program.");
        }
    }

    private static void printMenu() {
        System.out.println("\n1. Add Expense");
        System.out.println("2. View Expenses");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getChoice(Scanner scanner) {
        return scanner.nextInt();
    }

    private static void addExpense(Scanner scanner, ExpenseTracker expenseTracker) {
        System.out.print("Enter expense description: ");
        scanner.nextLine(); // Consume the newline character
        String description = scanner.nextLine();

        System.out.print("Enter expense amount: $");
        double amount = scanner.nextDouble();

        System.out.print("Enter expense date (yyyy-MM-dd): ");
        String dateString = scanner.next();

        expenseTracker.addExpense(description, amount, dateString);
    }
}
