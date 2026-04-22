package todo;

public class Task {
    private final String title;
    private final Priority priority;
    private boolean completed;

    public Task(String title, Priority priority) {
        if (title == null || title.isBlank())
            throw new IllegalArgumentException("El título no puede estar vacío");
        this.title = title;
        this.priority = priority;
        this.completed = false;
    }

    public String getTitle() { return title; }
    public Priority getPriority() { return priority; }
    public boolean isCompleted() { return completed; }
    public void complete() { this.completed = true; }

    @Override
    public String toString() {
        return String.format("[%s] %s (%s)", completed ? "X" : " ", title, priority);
    }
}