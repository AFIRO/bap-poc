package be.hogent.bappoc.log.dto;

import be.hogent.bappoc.log.entity.ActivityStatus;
import be.hogent.bappoc.log.entity.ActivityType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivitySoloInputDto {
    //Id van het process waarin het gekoppeld moet worden
    private String ProcessInstanceId;
    private String activityInstanceReference;
    private String subprocessInstanceReference;
    private LocalDateTime activityTimeStamp;
    private ActivityStatus activityStatus;
    private ActivityType activityType;
}
