package be.hogent.bappoc.log.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ProcessInstanceExecutionLog {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String logId;
    private String processInstanceReference;
    private String parentProcessInstanceReference;
    private String initiatorReference;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime processTimeStamp;
    @Enumerated(EnumType.STRING)
    private ActivityStatus activityStatus;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Activity> activities;
}
