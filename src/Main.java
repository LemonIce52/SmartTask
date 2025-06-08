import expensesAndIncome.ExpensesAndIncomeManager;
import Tasks.TaskManager;
import remiender.RemienderUI;

import java.util.Scanner;

public class Main{

    public  synchronized static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        ExpensesAndIncomeManager expensesAndIncomeManager = new ExpensesAndIncomeManager();
        RemienderUI remienderUI = new RemienderUI();
        char choice;

        do {
            System.out.println("****************************");
            System.out.println("HELLO SMART TASKER");
            System.out.println("T -> Show task option");
            System.out.println("M -> Show expenses and income option");
            System.out.println("S -> Set reminder");
            System.out.println("Q -> Exit");
            System.out.print("Enter choice: ");
            choice = scanner.next().toUpperCase().charAt(0);

            switch (choice){
                case 'T' -> taskManager.startUI(scanner);
                case 'M' -> expensesAndIncomeManager.startUI(scanner);
                case 'S' -> remienderUI.setReminder(scanner);
                case 'Q' -> System.out.println("GOOD BUE!");
                default -> System.out.println("Invalid choice. Please enter correct choice!");
            }
            System.out.println("****************************");
        } while (choice != 'Q');

        scanner.close();
    }
}