package be.hogent.bappoc.shared;

import be.hogent.bappoc.log.entity.Activity;
import be.hogent.bappoc.log.entity.ActivityStatus;
import be.hogent.bappoc.task.entity.Task;
import be.hogent.bappoc.task.entity.TaskStatus;
import be.hogent.bappoc.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class ExecutionEngine {
    private final TaskService taskService;
    private final EmployeeRepository employeeRepository;
    public void generateTaskBasedOnActivity(String processInstanceReference, Activity activity) {
        if (activity.getActivityStatus().equals(ActivityStatus.START) &&
                ActivityToTaskMapping.containsActivityReference(activity.getActivityReference())) {
            log.info("Generating task {} based on activity {} provided.", ActivityToTaskMapping.getTaskReference(activity.getActivityReference()), activity.getActivityReference());
            Task createdTask = Task.builder()
                    .taskInstanceReference(UUID.randomUUID().toString())
                    .taskReference(ActivityToTaskMapping.getTaskReference(activity.getActivityReference()))
                    .executorReference(employeeRepository.findFirstByOrderByNumberOfTasksAsc().getId())
                    .processInstanceReference(processInstanceReference)
                    .startTimeStamp(LocalDateTime.now())
                    .status(TaskStatus.ONGOING)
                    .build();
            taskService.persistNewTask(createdTask);
        } else {
            log.info("No new task to generate based on activity provided {}.", activity.getActivityReference());
        }
    }
}
