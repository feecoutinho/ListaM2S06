package devinhouse.listam2s06.model.transport;

import devinhouse.listam2s06.model.Assignee;
import devinhouse.listam2s06.model.Task;
import devinhouse.listam2s06.model.enums.PriorityEnum;
import devinhouse.listam2s06.model.enums.StatusEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record TaskDTO(
        Integer id,
        @NotBlank
        String description,
        LocalDate startDate,
        LocalDate endDate,
        @NotNull
        StatusEnum status,
        @NotNull
        PriorityEnum priority,
        @NotNull
        @Valid
        Assignee assignee) {

    public TaskDTO(Task task) {
        this(task.getId(), task.getDescription(), task.getStartDate(), task.getEndDate(), task.getStatus(), task.getPriority(), task.getAssignee());
    }
}