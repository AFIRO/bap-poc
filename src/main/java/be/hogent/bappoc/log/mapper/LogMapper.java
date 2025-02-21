package be.hogent.bappoc.log.mapper;

import be.hogent.bappoc.log.dto.ProcessInstanceOutputDto;
import be.hogent.bappoc.log.entity.ProcessInstanceExecutionLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LogMapper {
    private final ActivityMapper activityMapper;

    public ProcessInstanceOutputDto toOutputDto(ProcessInstanceExecutionLog data){
        return ProcessInstanceOutputDto.builder()
                .logId(data.getLogId())
                .activityStatus(data.getActivityStatus())
                .parentProcessInstanceReference(data.getParentProcessInstanceReference())
                .initiatorReference(data.getInitiatorReference())
                .processInstanceReference(data.getProcessInstanceReference())
                .processTimeStamp(data.getProcessTimeStamp())
                .activities(data.getActivities().stream().map(activityMapper::toActivityOutputDto).toList())
                .build();
    }
}
