package expensesAndIncome;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Scanner;

public class ExpensesAndIncomeUI {

    private final ExpensesAndIncomeService _expensesAndIncomeService;
    private final String _border = "****************************";

    public ExpensesAndIncomeUI(ExpensesAndIncomeService expensesAndIncomeService) {
        _expensesAndIncomeService = expensesAndIncomeService;
    }

    public void startExpensesAndIncome(Scanner scanner){

        char choice;

        do {
            System.out.println(_border);
            System.out.println("EXPENSES AND INCOME");
            System.out.println("S -> Show expenses and income");
            System.out.println("E -> Show expenses option");
            System.out.println("I -> Show income option");
            System.out.println("Q -> Come back");
            System.out.print("Enter choice: ");

            choice = scanner.next().toUpperCase().trim().charAt(0);

            switch (choice) {
                case 'S' -> readExpensiveAndIncome(scanner);
                case 'E' -> startExpenses(scanner);
                case 'I' -> startIncome(scanner);
                case 'Q' -> System.out.println("<----");
                default -> System.out.println("Invalid choice. Please enter correct choice!");
            }
            System.out.println(_border);
        } while (choice != 'Q');
    }

    private void startExpenses(Scanner scanner){
        char choice;

        do {
            System.out.println(_border);
            System.out.println("EXPENSES OPTIONS");
            System.out.println("W -> Write expenses");
            System.out.println("D -> Delete expenses");
            System.out.println("Q -> Come back");
            System.out.print("Enter choice: ");
            choice = scanner.next().toUpperCase().trim().charAt(0);

            switch (choice){
                case 'W' -> writeExpenses(scanner);
                case 'D' -> deleteExpenses(scanner);
                case 'Q' -> System.out.println("<----");
                default -> System.out.println("Invalid choice. Please enter correct choice!");
            }

            System.out.println(_border);
        } while (choice != 'Q');
    }

    private void startIncome(Scanner scanner){
        char choice;

        do {
            System.out.println(_border);
            System.out.println("INCOME OPTIONS");
            System.out.println("W -> Write Income");
            System.out.println("D -> Delete Income");
            System.out.println("Q -> Come back");
            System.out.print("Enter choice: ");
            choice = scanner.next().toUpperCase().trim().charAt(0);

            switch (choice){
                case 'W' -> writeIncome(scanner);
                case 'D' -> deleteIncome(scanner);
                case 'Q' -> System.out.println("<----");
                default -> System.out.println("Invalid choice. Please enter correct choice!");
            }

            System.out.println(_border);
        } while (choice != 'Q');
    }

    private void readExpensiveAndIncome(Scanner scanner) {
        scanner.nextLine();
        System.out.println(_border);
        HashMap<LocalDate, Double> expenses = _expensesAndIncomeService.totalExpenses();
        HashMap<LocalDate, Double> income = _expensesAndIncomeService.totalIncome();
        System.out.println("INCOME");
        outputExpensesAndIncome(income);
        System.out.println("EXPENSES");
        outputExpensesAndIncome(expenses);
        System.out.print("PRESS \"ENTER\" TO EXIT");
        scanner.nextLine();
        System.out.println(_border);
    }

    private void writeExpenses(Scanner scanner) {
        scanner.nextLine();
        System.out.println(_border);
        while (true) {
            System.out.print("Enter to expenses: ");
            try {
                double expenses = Double.parseDouble(scanner.nextLine().trim());
                boolean isWrite = _expensesAndIncomeService.appendExpenses(expenses);

                if (!isWrite){
                    System.out.println("An error occurred while writing or incorrect values were entered!");
                } else {
                    System.out.println("Write is success!");
                }

                break;
            } catch (NumberFormatException e){
                System.out.println("Please enter to correct expenses!");
            }
        }
        System.out.println(_border);
    }

    private void deleteExpenses(Scanner scanner){
        scanner.nextLine();
        String date;
        do{
            System.out.print("Please enter date expenses (yyyy-mm-dd): ");
            date = scanner.nextLine().trim();

            try {
                LocalDate enterDate = LocalDate.parse(date);
                if (_expensesAndIncomeService.deleteExpenses(enterDate))
                    System.out.println("Delete success");
                else
                    System.out.println("There was an error during deletion or there are no expenses for this date!");

                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date. Please enter correct date (yyyy-mm-dd)!");
            }
        } while (true);
    }

    private void deleteIncome(Scanner scanner){
        scanner.nextLine();
        String date;
        do{
            System.out.print("Please enter date income (yyyy-mm-dd): ");
            date = scanner.nextLine().trim();

            try {
                LocalDate enterDate = LocalDate.parse(date);
                if (_expensesAndIncomeService.deleteIncome(enterDate))
                    System.out.println("Delete success");
                else
                    System.out.println("There was an error during deletion or there are no income for this date!");

                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date. Please enter correct date (yyyy-mm-dd)!");
            }
        } while (true);
    }

    public void writeIncome(Scanner scanner) {
        scanner.nextLine();
        System.out.println(_border);
        while (true) {
            System.out.print("Enter to income: ");
            try {
                double income = Double.parseDouble(scanner.nextLine().trim());
                boolean isWrite = _expensesAndIncomeService.appendIncome(income);

                if (!isWrite){
                    System.out.println("An error occurred while writing or incorrect values were entered!");
                } else {
                    System.out.println("Write is success!");
                }

                break;
            } catch (NumberFormatException e){
                System.out.println("Please enter to correct expenses!");
            }
        }
        System.out.println(_border);
    }

    private void outputExpensesAndIncome(HashMap<LocalDate, Double> expensesOrIncome) {
        if (!expensesOrIncome.isEmpty()) {
            for (LocalDate date : expensesOrIncome.keySet()) {
                System.out.println(date + ": " + expensesOrIncome.get(date));
            }
        }
        else{
            System.out.println("It's empty here for now");
        }
    }

}
