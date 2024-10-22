import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Serializable {
    private String name;
    private String description;
    private LocalDate dueDate;
    private String priority;
    private boolean isComplete;

    // Constructor to create a new task
    public Task(String name, String description, LocalDate dueDate, String priority) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.isComplete = false; // Tasks are incomplete by default
    }

    // Getter methods to access task details
    public String getName() { return name; }
    public String getDescription() { return description; }
    public LocalDate getDueDate() { return dueDate; }
    public String getPriority() { return priority; }
    public boolean isComplete() { return isComplete; }

    // Mark a task as complete
    public void markAsComplete() {
        this.isComplete = true;
    }

    // Returns a string representation of the task for easy printing
    @Override
    public String toString() {
        return "Task: " + name + "\nDescription: " + description + "\nDue: " + dueDate +
               "\nPriority: " + priority + "\nStatus: " + (isComplete ? "Completed" : "Pending");
    }
}
