package todo;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        manager.addTask("Preparar Estado del Arte del TFG", Priority.HIGH);
        manager.addTask("Estudiar SSDD", Priority.HIGH);
        manager.addTask("Hacer la compra", Priority.LOW);
        manager.addTask("Hacer entrega CoMov", Priority.MEDIUM);

        System.out.println("=== TODAS LAS TAREAS (por prioridad) ===");
        manager.getTasksByPriority().forEach(System.out::println);

        manager.completeTask("Hacer entrega CoMov");

        System.out.println("\n=== TAREAS PENDIENTES ===");
        manager.getPendingTasks().forEach(System.out::println);
    }
}