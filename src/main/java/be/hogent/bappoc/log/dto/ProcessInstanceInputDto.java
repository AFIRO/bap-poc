package be.hogent.bappoc.log.dto;

import be.hogent.bappoc.log.entity.ActivityStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessInstanceInputDto {
    @NotNull(message = "Process Instance Reference is required")
    private String processInstanceReference;
    private String parentProcessInstanceReference;
    private String initiatorReference;
    private LocalDateTime processTimeStamp;
    private ActivityStatus activityStatus;
    private List<ActivitySoloInputDto> activities;
}
