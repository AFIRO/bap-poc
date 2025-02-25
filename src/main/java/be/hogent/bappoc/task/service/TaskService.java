package be.hogent.bappoc.task.service;

import be.hogent.bappoc.shared.Employee;
import be.hogent.bappoc.shared.EmployeeRepository;
import be.hogent.bappoc.task.TaskMapper;
import be.hogent.bappoc.task.dto.TaskInputDto;
import be.hogent.bappoc.task.dto.TaskOutputDto;
import be.hogent.bappoc.task.entity.Task;
import be.hogent.bappoc.task.entity.TaskStatus;
import be.hogent.bappoc.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper mapper;
    private final EmployeeRepository employeeRepository;

    public List<TaskOutputDto> getAll() {
        log.info("Getting data from repo");
        List<Task> data = taskRepository.findAll();
        return data
                .stream()
                .map(mapper::toOutputDto)
                .sorted(Comparator.comparing(TaskOutputDto::getStartTimeStamp))
                .toList();
    }

    public TaskOutputDto getByTaskInstanceReference(String taskInstanceReference) {
        log.info("Getting data from repo");
        Task data = taskRepository.getTaskByTaskInstanceReference(taskInstanceReference).orElseThrow();
        return mapper.toOutputDto(data);
    }

    public TaskOutputDto getByExecutorReference(String executorReference) {
        log.info("Getting data from repo");
        Task data = taskRepository.getTaskByExecutorReference(executorReference).orElseThrow();
        return mapper.toOutputDto(data);
    }

    public TaskOutputDto getByInitiatorReference(String initiatorReference) {
        log.info("Getting data from repo");
        Task data = taskRepository.getTaskByInitiatorReference(initiatorReference).orElseThrow();
        return mapper.toOutputDto(data);
    }

    public void persistNewTask(Task createdTask ) {
        taskRepository.save(createdTask);
    }

    public void processTaskData(TaskInputDto data) {
        if (!taskRepository.existsByTaskInstanceReference(data.getTaskInstanceReference())) {
            log.error("Task with Instance Reference {} does not exist.", data.getTaskInstanceReference());
            throw new IllegalArgumentException("Task does not exist.");
        }

        log.info("Updating data for task {}",data.getTaskInstanceReference());
        Task dataToUpdate = taskRepository.getTaskByTaskInstanceReference(data.getTaskInstanceReference()).orElseThrow();
        dataToUpdate = mapper.updateData(dataToUpdate,data);
        taskRepository.save(dataToUpdate);
        updateEmployeeWorkload(dataToUpdate);
    }

    private void updateEmployeeWorkload(Task data) {
        if (data.getStatus() == TaskStatus.SUCCESS || data.getStatus() == TaskStatus.FAIL){
            Employee employeeToUpdate = employeeRepository.findById(data.getExecutorReference()).orElseThrow();
            employeeToUpdate.setNumberOfTasks((employeeToUpdate.getNumberOfTasks()-1));
            employeeRepository.save(employeeToUpdate);
        }
    }
}
