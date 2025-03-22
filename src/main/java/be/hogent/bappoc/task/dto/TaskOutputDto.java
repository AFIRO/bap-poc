package be.hogent.bappoc.task.dto;

import be.hogent.bappoc.task.entity.TaskStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskOutputDto {
    private String taskInstanceReference;
    private String executorReference;
    private String processInstanceReference;
    private String taskReference;
    private String initiatorReference;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTimeStamp;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime stopTimeStamp;
    private TaskStatus status;
}
