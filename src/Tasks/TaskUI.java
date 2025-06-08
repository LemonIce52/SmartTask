package Tasks;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskUI {

    private final TaskService _taskService;
    private final String _border = "****************************";

    public TaskUI(TaskService taskService){
        _taskService = taskService;
    }

    public void startTask(Scanner scanner){
        char choice;

        do {
            System.out.println("****************************");
            System.out.println("Hello in SmartTask");
            System.out.println("W -> Write tasks.");
            System.out.println("R -> Print tasks.");
            System.out.println("D -> Delete task.");
            System.out.println("Q -> Exit");
            System.out.print("Enter choice: ");
            choice = scanner.next().toUpperCase().charAt(0);
            scanner.nextLine();

            switch(choice){
                case 'W' -> writeTask(scanner);
                case 'R' -> readTask(scanner);
                case 'D' -> deleteTask(scanner);
                case 'Q' -> System.out.println("GOOD BYE!");
                default -> System.out.println("Invalid choice!");
            }
        } while(choice != 'Q');

    }

    private void writeTask(Scanner scanner) {
        System.out.println(_border);
        System.out.print("Enter your task: ");
        String response = scanner.nextLine();
        boolean isWrite = _taskService.writeTask(response);
        String message = isWrite ? "Tasks write success" : "An error occurred or task is empty!";
        System.out.println(message);
        System.out.println(_border);
    }

    private void readTask(Scanner scanner) {
        ArrayList<Task> listTasks = _taskService.readTask();
        System.out.println(_border);
        System.out.println("TASKS:");
        if (!listTasks.isEmpty()) {
            for (Task task : listTasks)
                System.out.println(task);
        } else {
            System.out.println("There are no tasks yet!");
        }

        System.out.print("PRESS \"ENTER\" TO EXIT");
        scanner.nextLine();
        System.out.println(_border);
    }

    private void deleteTask(Scanner scanner) {
        int numberTask;

        System.out.println(_border);
        while (true) {
            System.out.print("Enter to number task: ");
            try {
                numberTask = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice! Please enter to correct number!");
                continue;
            }


            if (numberTask <= 0) {
                System.out.println("Enter valid number task!");
            } else {
                boolean isRemove = _taskService.deleteTask(numberTask);
                String message = isRemove ? "Remove complete!" : "Tasks with number does not exist or an error occurred!";
                System.out.println(message);
                break;
            }
        }
        System.out.println(_border);
    }

}
