package be.hogent.bappoc.task;

import be.hogent.bappoc.task.dto.TaskInputDto;
import be.hogent.bappoc.task.dto.TaskOutputDto;
import be.hogent.bappoc.task.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public TaskOutputDto toOutputDto(Task data){
        return TaskOutputDto.builder()
                .taskId(data.getTaskId())
                .taskInstanceReference(data.getTaskInstanceReference())
                .processInstanceReference(data.getProcessInstanceReference())
                .executorReference(data.getExecutorReference())
                .startTimeStamp(data.getStartTimeStamp())
                .stopTimeStamp(data.getStopTimeStamp())
                .status(data.getStatus())
                .build();
    }
}
