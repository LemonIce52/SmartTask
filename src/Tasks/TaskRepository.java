package Tasks;

import java.util.ArrayList;

public interface TaskRepository {
    ArrayList<String> readTaskFile();
    boolean savedTask(ArrayList<Task> tasks);
    boolean appendTaskToFile(String task, int size);
}
