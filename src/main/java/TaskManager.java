import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks; // List to hold tasks
    private static final String FILE_NAME = "tasks.ser"; // File to store tasks

    // Constructor to load tasks from a file when the program starts
    public TaskManager() {
        tasks = loadTasksFromFile();
    }

    // Method to add a new task to the list
    public void addTask(String name, String description, LocalDate dueDate, String priority) {
        tasks.add(new Task(name, description, dueDate, priority));
        saveTasksToFile(); // Save updated task list to file
    }

    // Method to display all tasks
    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i)); // Printing task information
                System.out.println("-------------------");
            }
        }
    }

    // Method to mark a task as complete by its index
    public void markTaskAsComplete(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            tasks.get(taskIndex).markAsComplete();
            saveTasksToFile(); // Save updated list
        } else {
            System.out.println("Invalid task index.");
        }
    }

    // Method to delete a task by its index
    public void deleteTask(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            tasks.remove(taskIndex);
            saveTasksToFile(); // Save updated list
        } else {
            System.out.println("Invalid task index.");
        }
    }

    // Method to save the list of tasks to a file using serialization
    private void saveTasksToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    // Method to load tasks from the file when the program starts
    @SuppressWarnings("unchecked")
    private List<Task> loadTasksFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>(); // Return empty list if no file exists
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Task>) ois.readObject(); // Read tasks from file
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
