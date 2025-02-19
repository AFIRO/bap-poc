package be.hogent.bappoc.log.dto;

import be.hogent.bappoc.log.entity.Activity;
import be.hogent.bappoc.log.entity.ActivityStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
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
public class ProcessInstanceOutputDto {
    private String logId;
    private String processInstanceReference;
    private String parentProcessInstanceReference;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime processTimeStamp;
    @Enumerated(EnumType.STRING)
    private ActivityStatus activityStatus;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ActivityOutputDto> activities;
}
