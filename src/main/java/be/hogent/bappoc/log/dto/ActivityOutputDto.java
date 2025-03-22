package be.hogent.bappoc.log.dto;

import be.hogent.bappoc.log.entity.ActivityStatus;
import be.hogent.bappoc.log.entity.ActivityType;
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
public class ActivityOutputDto {
    private String activityInstanceReference;
    private String subprocessInstanceReference;
    private String activityReference;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime activityTimeStamp;
    private String executorReference;
    private ActivityStatus activityStatus;
    private ActivityType activityType;
}
