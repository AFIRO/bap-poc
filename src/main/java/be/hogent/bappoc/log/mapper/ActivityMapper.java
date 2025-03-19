package be.hogent.bappoc.log.mapper;

import be.hogent.bappoc.log.dto.ActivityInputDto;
import be.hogent.bappoc.log.dto.ActivityOutputDto;
import be.hogent.bappoc.log.entity.Activity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ActivityMapper {
    public ActivityOutputDto toActivityOutputDto(Activity data){
        return ActivityOutputDto.builder()
                .activityId(data.getActivityId())
                .activityInstanceReference(data.getActivityInstanceReference())
                .activityTimeStamp(data.getActivityTimeStamp())
                .subprocessInstanceReference(data.getSubprocessInstanceReference())
                .activityReference(data.getActivityReference())
                .activityStatus(data.getActivityStatus())
                .activityType(data.getActivityType())
                .executorReference(data.getExecutorReference())
                .build();
    }
    public Activity toEntity(ActivityInputDto data){
        return Activity.builder()
                .activityInstanceReference(UUID.randomUUID().toString())
                .activityReference(data.getActivityReference())
                .activityTimeStamp(data.getActivityTimeStamp())
                .activityStatus(data.getActivityStatus())
                .activityType(data.getActivityType())
                .executorReference(data.getExecutorReference())
                .build();
    }
}
