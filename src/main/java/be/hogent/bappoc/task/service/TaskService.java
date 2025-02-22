package be.hogent.bappoc.task.service;

import be.hogent.bappoc.task.TaskMapper;
import be.hogent.bappoc.task.dto.TaskInputDto;
import be.hogent.bappoc.task.dto.TaskOutputDto;
import be.hogent.bappoc.task.entity.Task;
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
    private final TaskRepository repository;
    private final TaskMapper mapper;

    public List<TaskOutputDto> getAll() {
        log.info("Getting data from repo");
        List<Task> data = repository.findAll();
        return data
                .stream()
                .map(mapper::toOutputDto)
                .sorted(Comparator.comparing(TaskOutputDto::getStartTimeStamp))
                .toList();
    }

    public TaskOutputDto getByTaskInstanceReference(String taskInstanceReference) {
        log.info("Getting data from repo");
        Task data = repository.getTaskByTaskInstanceReference(taskInstanceReference).orElseThrow();
        return mapper.toOutputDto(data);
    }

    public TaskOutputDto getByExecutorReference(String executorReference) {
        log.info("Getting data from repo");
        Task data = repository.getTaskByExecutorReference(executorReference).orElseThrow();
        return mapper.toOutputDto(data);
    }

    public TaskOutputDto getByInitiatorReference(String initiatorReference) {
        log.info("Getting data from repo");
        Task data = repository.getTaskByInitiatorReference(initiatorReference).orElseThrow();
        return mapper.toOutputDto(data);
    }

    public void persistNewTask(Task createdTask ) {
        repository.save(createdTask);
    }

    public void processTaskData(TaskInputDto data) {
        if (!repository.existsByTaskInstanceReference(data.getTaskInstanceReference())) {
            log.error("Task with Instance Reference {} does not exist.", data.getTaskInstanceReference());
            throw new IllegalArgumentException("Task does not exist.");
        }

        log.info("Updating data for task {}",data.getTaskInstanceReference());
        Task dataToUpdate = repository.getTaskByTaskInstanceReference(data.getTaskInstanceReference()).orElseThrow();
        dataToUpdate = mapper.updateData(dataToUpdate,data);
        repository.save(dataToUpdate);
    }
}
