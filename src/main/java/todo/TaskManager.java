package todo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TaskManager {
    private final List<Task> tasks = new ArrayList<>();

    public void addTask(String title, Priority priority) {
        tasks.add(new Task(title, priority));
    }

    public boolean completeTask(String title) {
        for (Task t : tasks) {
            if (t.getTitle().equals(title)) {
                t.complete();
                return true;
            }
        }
        return false;
    }

    public boolean deleteTask(String title) {
        return tasks.removeIf(t -> t.getTitle().equals(title));
    }

    public List<Task> getTasksByPriority() {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getPriority))
                .toList();
    }

    public List<Task> getPendingTasks() {
        return tasks.stream()
                .filter(t -> !t.isCompleted())
                .toList();
    }

    public int size() { return tasks.size(); }
}