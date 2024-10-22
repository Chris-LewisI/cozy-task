import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    private List<Task> tasks;
    private static final String FILE_NAME = "tasks.ser";

    public TaskManager() {
        tasks = loadTasksFromFile();
    }

    // Add a new task
    public void addTask(String name, String description, LocalDate dueDate, String priority) {
        tasks.add(new Task(name, description, dueDate, priority));
        saveTasksToFile();
    }

    // View all tasks
    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
                System.out.println("-------------------");
            }
        }
    }

    // Mark a task as complete
    public void markTaskAsComplete(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            tasks.get(taskIndex).markAsComplete();
            saveTasksToFile();
        } else {
            System.out.println("Invalid task index.");
        }
    }

    // Delete a task
    public void deleteTask(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            tasks.remove(taskIndex);
            saveTasksToFile();
        } else {
            System.out.println("Invalid task index.");
        }
    }

    // Save tasks to a file
    private void saveTasksToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    // Load tasks from a file
    @SuppressWarnings("unchecked")
    private List<Task> loadTasksFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Task>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
