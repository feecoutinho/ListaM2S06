package devinhouse.listam2s06.controller;

import devinhouse.listam2s06.Exceptions.NotFoundException;
import devinhouse.listam2s06.model.enums.StatusEnum;
import devinhouse.listam2s06.model.transport.TaskDTO;
import devinhouse.listam2s06.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskDTO> create(@Valid @RequestBody TaskDTO taskDTO) {
        TaskDTO response = this.taskService.create(taskDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> list(@RequestParam(name = "status", required = false) StatusEnum status, @RequestParam(name = "assignee", required = false) String name) {
        List<TaskDTO> response = this.taskService.listAll(status, name);
        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> update(@PathVariable("id") Integer id, @RequestBody TaskDTO taskDTO) throws NotFoundException {
        TaskDTO response = this.taskService.update(id, taskDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        this.taskService.delete(id);
        return ResponseEntity.ok().build();
    }
}
