import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Serializable {
    private String name;
    private String description;
    private LocalDate dueDate;
    private String priority;
    private boolean isComplete;

    public Task(String name, String description, LocalDate dueDate, String priority) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.isComplete = false;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void markAsComplete() {
        this.isComplete = true;
    }

    @Override
    public String toString() {
        return "Task: " + name + "\nDescription: " + description + "\nDue: " + dueDate +
               "\nPriority: " + priority + "\nStatus: " + (isComplete ? "Completed" : "Pending");
    }
}
