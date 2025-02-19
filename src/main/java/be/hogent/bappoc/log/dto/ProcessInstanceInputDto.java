package be.hogent.bappoc.log.dto;

import be.hogent.bappoc.log.entity.ActivityStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessInstanceInputDto {
    private String processInstanceReference;
    private String parentProcessInstanceReference;
    private LocalDateTime processTimeStamp;
    private ActivityStatus activityStatus;
    private List<ActivitySoloInputDto> activities;
}
