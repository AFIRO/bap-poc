package be.hogent.bappoc.task.dto;

import be.hogent.bappoc.task.entity.TaskStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskInputDto {
    @NotNull(message = "Task Instance Reference can not be empty")
    private String taskInstanceReference;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime stopTimeStamp;
    private TaskStatus status;
}
