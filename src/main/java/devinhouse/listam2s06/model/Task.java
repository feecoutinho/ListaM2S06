package devinhouse.listam2s06.model;

import devinhouse.listam2s06.database.Database;
import devinhouse.listam2s06.model.enums.PriorityEnum;
import devinhouse.listam2s06.model.enums.StatusEnum;
import devinhouse.listam2s06.model.transport.TaskDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Task {

    private Integer id;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private StatusEnum status;
    private PriorityEnum priority;
    private Assignee assignee;


    public Task(TaskDTO taskDTO) {
        this.id = Database.setId();
        this.description = taskDTO.description();
        this.startDate = taskDTO.startDate();
        this.endDate = taskDTO.endDate();
        this.status = taskDTO.status();
        this.priority = taskDTO.priority();
        this.assignee = taskDTO.assignee();
    }

}