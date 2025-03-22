package be.hogent.bappoc.log.mapper;

import be.hogent.bappoc.log.dto.ActivityOutputDto;
import be.hogent.bappoc.log.dto.ProcessInstanceInputDto;
import be.hogent.bappoc.log.dto.ProcessInstanceOutputDto;
import be.hogent.bappoc.log.entity.ProcessInstanceExecutionLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class LogMapper {
    private final ActivityMapper activityMapper;

    public ProcessInstanceOutputDto toOutputDto(ProcessInstanceExecutionLog data){
        return ProcessInstanceOutputDto.builder()
                .activityStatus(data.getActivityStatus())
                .parentProcessInstanceReference(data.getParentProcessInstanceReference())
                .initiatorReference(data.getInitiatorReference())
                .processInstanceReference(data.getProcessInstanceReference())
                .processTimeStamp(data.getProcessTimeStamp())
                .activities(data.getActivities()
                        .stream()
                        .map(activityMapper::toActivityOutputDto)
                        .sorted(Comparator.comparing(ActivityOutputDto::getActivityTimeStamp))
                        .toList())
                .build();
    }

    public ProcessInstanceExecutionLog toEntity(ProcessInstanceInputDto data){
        return ProcessInstanceExecutionLog.builder()
                .processInstanceReference(UUID.randomUUID().toString())
                .initiatorReference(data.getInitiatorReference())
                .processTimeStamp(data.getProcessTimeStamp())
                .parentProcessInstanceReference(data.getParentProcessInstanceReference())
                .activityStatus(data.getActivityStatus())
                .activities(List.of(activityMapper.toEntity(data.getActivities().get(0))))
                .build();
    }
}
