package todo;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskManagerTest {

    private TaskManager manager;

    @BeforeEach
    void setUp() {
        manager = new TaskManager();
    }

    

    // TEST CORRECTO
    @Test
    void addTaskIncreasesSize() {
        manager.addTask("Estudiar CI/CD", Priority.HIGH);
        assertEquals(1, manager.size());
    }

    
   /*
    // TEST ROTO
    @Test
    void addTaskIncreasesSize() {
        manager.addTask("Estudiar CI/CD", Priority.HIGH);
        assertEquals(2, manager.size()); // ← cambiamos 1 por 2 (no hay 2 tareas, hemos añadido solo una)
    }
    
    */

    @Test
    void addTaskWithEmptyTitleThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> manager.addTask("", Priority.LOW));
    }

    @Test
    void completeTaskMarksItAsDone() {
        manager.addTask("Hacer memoria", Priority.HIGH);
        assertTrue(manager.completeTask("Hacer memoria"));
        assertTrue(manager.getTasksByPriority().get(0).isCompleted());
    }

    @Test
    void completeNonExistentTaskReturnsFalse() {
        assertFalse(manager.completeTask("Tarea inexistente"));
    }

    @Test
    void deleteTaskRemovesIt() {
        manager.addTask("Tarea a borrar", Priority.LOW);
        assertTrue(manager.deleteTask("Tarea a borrar"));
        assertEquals(0, manager.size());
    }

    @Test
    void getTasksByPriorityReturnsSortedList() {
        manager.addTask("Tarea baja", Priority.LOW);
        manager.addTask("Tarea alta", Priority.HIGH);
        manager.addTask("Tarea media", Priority.MEDIUM);

        List<Task> sorted = manager.getTasksByPriority();
        assertEquals(Priority.HIGH, sorted.get(0).getPriority());
        assertEquals(Priority.MEDIUM, sorted.get(1).getPriority());
        assertEquals(Priority.LOW, sorted.get(2).getPriority());
    }

    @Test
    void getPendingTasksExcludesCompleted() {
        manager.addTask("Tarea 1", Priority.HIGH);
        manager.addTask("Tarea 2", Priority.LOW);
        manager.completeTask("Tarea 1");

        assertEquals(1, manager.getPendingTasks().size());
        assertEquals("Tarea 2", manager.getPendingTasks().get(0).getTitle());
    }

    @Test
    void deleteNonExistentTaskReturnsFalse() {
        assertFalse(manager.deleteTask("No existe"));
    }
}