package be.hogent.bappoc.task;

import be.hogent.bappoc.task.dto.TaskInputDto;
import be.hogent.bappoc.task.dto.TaskOutputDto;
import be.hogent.bappoc.task.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public TaskOutputDto toOutputDto(Task data){
        return TaskOutputDto.builder()
                .taskInstanceReference(data.getTaskInstanceReference())
                .processInstanceReference(data.getProcessInstanceReference())
                .taskReference(data.getTaskReference())
                .initiatorReference(data.getInitiatorReference())
                .executorReference(data.getExecutorReference())
                .startTimeStamp(data.getStartTimeStamp())
                .stopTimeStamp(data.getStopTimeStamp())
                .status(data.getStatus())
                .build();
    }

    public Task updateData(Task dataToUpdate, TaskInputDto data) {
        if (data.getStopTimeStamp() != null) { dataToUpdate.setStopTimeStamp(data.getStopTimeStamp());}
        if (data.getStatus() != null) {dataToUpdate.setStatus(data.getStatus());}
        return dataToUpdate;
    }
}
