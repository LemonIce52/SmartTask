package Tasks;

import java.util.ArrayList;


public class TaskService {

    private final FIleTaskRepository _fIleTaskRepository;

    public TaskService(FIleTaskRepository fIleTaskRepository){
        _fIleTaskRepository = fIleTaskRepository;
    }

    public boolean writeTask(String task) {
        // method for write task file

        if (task != null && !task.trim().isEmpty()) {

            ArrayList<String> readTasks = _fIleTaskRepository.readTaskFile();
            int sizeWrites = readTasks.size();

            return _fIleTaskRepository.appendTaskToFile(task, sizeWrites);
        }

        return false;
    }

    public ArrayList<Task> readTask() {
        //method return formated tasks
        ArrayList<String> readTasks = _fIleTaskRepository.readTaskFile();
        ArrayList<Task> createReturnTasks = new ArrayList<>();
        int currentId = 1;

        for (String line : readTasks) {
            String[] splitLine = line.split(":", 2);

            if (splitLine.length < 2) continue;

            String text = splitLine[1].trim();


            createReturnTasks.add(new Task(currentId++, text));
        }

        return createReturnTasks;
    }

    public boolean deleteTask(Integer numberTask) {
        //method remove task
        if (numberTask == null) return false;

        ArrayList<Task> listTask = new ArrayList<>();
        ArrayList<Task> readTasks = readTask();
        boolean isNumber = false;

        for (Task task : readTasks) {
            if (task.id() != numberTask) {
                listTask.add(task);
            } else
                isNumber = true;

        }

        return isNumber && _fIleTaskRepository.savedTask(listTask);
    }
}