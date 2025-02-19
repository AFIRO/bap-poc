package be.hogent.bappoc.log.dto;

import be.hogent.bappoc.log.entity.ActivityStatus;
import be.hogent.bappoc.log.entity.ActivityType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    private String activityId;
    private String activityInstanceReference;
    private String subprocessInstanceReference;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime activityTimeStamp;
    private ActivityStatus activityStatus;
    private ActivityType activityType;
}
