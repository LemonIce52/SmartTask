package Tasks;

import java.util.Scanner;

public class TaskManager {

    public void startUI(Scanner scanner){
        FIleTaskRepository repo = new FIleTaskRepository();
        TaskService service = new TaskService(repo);
        TaskUI ui = new TaskUI(service);

        ui.startTask(scanner);
    }

}
