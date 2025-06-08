package Tasks;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FIleTaskRepository implements TaskRepository {

    static {
        File dir = new File("system");
        if (!dir.exists())
            dir.mkdir();
    }

    private final String _filePath = "system/task.txt";
    private final Logger _logger = Logger.getLogger(FIleTaskRepository.class.getName());

    @Override
    public ArrayList<String> readTaskFile() {
        //method read tasks from file
        ArrayList<String> listTasks = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(_filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                listTasks.add(line);
            }
        } catch (FileNotFoundException e) {
            _logger.log(Level.SEVERE, "File not found", e);
        } catch (IOException e) {
            _logger.log(Level.SEVERE, "Error reading file", e);
        }

        return listTasks;
    }

    @Override
    public boolean savedTask(ArrayList<Task> tasks) {
        //method write task on file
        try (FileWriter fileWriter = new FileWriter(_filePath)) {
            for (int i = 1; i <= tasks.size(); i++) {
                fileWriter.write(i + ":" + tasks.get(i - 1).text() + "\n");
            }

            return true;
        } catch (IOException e) {
            _logger.log(Level.SEVERE, "Error writing file", e);
        }

        return false;
    }

    @Override
    public boolean appendTaskToFile(String task, int size) {
        //overload method write task on file
        try (FileWriter fileWriter = new FileWriter(_filePath, true)) {
            fileWriter.write((size + 1) + ":" + task + "\n");

            return true;
        } catch (IOException e) {
            _logger.log(Level.SEVERE, "Error writing file", e);
        }

        return false;
    }
}
