package devinhouse.listam2s06.database;

import devinhouse.listam2s06.Exceptions.NotFoundException;
import devinhouse.listam2s06.model.Task;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private static Integer nextId = 0;
    private static List<Task> tasks = new ArrayList<>();

    public static void add(Task task) {
        Database.tasks.add(task);
    }

    public static void remove(Integer id) {
        Database.tasks.removeIf(task -> task.getId().equals(id));
    }

    public static List<Task> listAll() {
        return Database.tasks;
    }

    public static Task get(Integer id) throws NotFoundException {
        return Database.tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst().orElseThrow(() -> new NotFoundException("Tarefa não encontrada."));
    }

    public static Integer setId() {
        Database.nextId = Database.nextId + 1;
        return Database.nextId;
    }
}