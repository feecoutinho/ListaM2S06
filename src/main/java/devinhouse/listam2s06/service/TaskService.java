package devinhouse.listam2s06.service;

import devinhouse.listam2s06.Exceptions.NotFoundException;
import devinhouse.listam2s06.database.Database;
import devinhouse.listam2s06.model.Task;
import devinhouse.listam2s06.model.enums.StatusEnum;
import devinhouse.listam2s06.model.transport.TaskDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    public TaskDTO create(TaskDTO taskDTO) {
        Task task = new Task(taskDTO);
        Database.add(task);
        return new TaskDTO(task);
    }

    public List<TaskDTO> listAll(StatusEnum status, String name) {
        List<Task> tasks = Database.listAll();
        if (status != null) {
            return tasks.stream().filter(task -> task.getStatus().equals(status))
                    .map(TaskDTO::new).toList();
        }

        if (name != null) {
            return tasks.stream()
                    .filter(task -> task.getAssignee().getName().equalsIgnoreCase(name))
                    .map(TaskDTO::new).toList();
        }

        return tasks.stream()
                .map(TaskDTO::new).toList();
    }

    public TaskDTO update(Integer id, TaskDTO taskDTO) throws NotFoundException {
        Task task = Database.get(id);
        task.setStatus(taskDTO.status());
        return new TaskDTO(task);
    }

    public void delete(Integer id) {
        Database.remove(id);
    }
}