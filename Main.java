import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nTask Manager:");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Mark Task as Complete");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            switch (option) {
                case 1:
                    addNewTask(taskManager, scanner);
                    break;
                case 2:
                    taskManager.viewTasks();
                    break;
                case 3:
                    markTaskComplete(taskManager, scanner);
                    break;
                case 4:
                    deleteTask(taskManager, scanner);
                    break;
                case 5:
                    System.out.println("Exiting the program.");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    // Method to add a new task
    private static void addNewTask(TaskManager taskManager, Scanner scanner) {
        System.out.print("Enter task name: ");
        String name = scanner.nextLine();

        System.out.print("Enter task description: ");
        String description = scanner.nextLine();

        LocalDate dueDate = null;
        while (dueDate == null) {
            System.out.print("Enter due date (YYYY-MM-DD): ");
            try {
                dueDate = LocalDate.parse(scanner.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Try again.");
            }
        }

        System.out.print("Enter priority (Low, Medium, High): ");
        String priority = scanner.nextLine();

        taskManager.addTask(name, description, dueDate, priority);
        System.out.println("Task added successfully.");
    }

    // Method to mark a task as complete
    private static void markTaskComplete(TaskManager taskManager, Scanner scanner) {
        System.out.print("Enter task number to mark as complete: ");
        int taskIndex = scanner.nextInt() - 1;
        taskManager.markTaskAsComplete(taskIndex);
    }

    // Method to delete a task
    private static void deleteTask(TaskManager taskManager, Scanner scanner) {
        System.out.print("Enter task number to delete: ");
        int taskIndex = scanner.nextInt() - 1;
        taskManager.deleteTask(taskIndex);
    }
}
