package be.hogent.bappoc.log.mapper;

import be.hogent.bappoc.log.dto.ActivityOutputDto;
import be.hogent.bappoc.log.entity.Activity;
import org.springframework.stereotype.Component;

@Component
public class ActivityMapper {
    public ActivityOutputDto toActivityOutputDto(Activity data){
        return ActivityOutputDto.builder()
                .activityId(data.getActivityId())
                .activityInstanceReference(data.getActivityInstanceReference())
                .activityTimeStamp(data.getActivityTimeStamp())
                .subprocessInstanceReference(data.getSubprocessInstanceReference())
                .activityStatus(data.getActivityStatus())
                .activityType(data.getActivityType())
                .build();
    }
}
