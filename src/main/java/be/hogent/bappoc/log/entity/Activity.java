package be.hogent.bappoc.log.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Activity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String activityId;
    private String activityInstanceReference;
    private String subprocessInstanceReference;
    private String activityReference;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime activityTimeStamp;
    @Enumerated(EnumType.STRING)
    private ActivityStatus activityStatus;
    @Enumerated(EnumType.STRING)
    private ActivityType activityType;

}
