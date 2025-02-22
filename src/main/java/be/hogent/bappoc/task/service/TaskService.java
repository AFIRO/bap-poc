package be.hogent.bappoc.task.service;

import be.hogent.bappoc.task.dto.TaskInputDto;
import be.hogent.bappoc.task.dto.TaskOutputDto;
import be.hogent.bappoc.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class TaskService {
    private final TaskRepository repository;

    public List<TaskOutputDto> getAll() {
        return null;
    }

    public TaskOutputDto getByTaskInstanceReference(String taskInstanceReference) {
        return null;
    }

    public TaskOutputDto getByExecutorReference(String executorReference) {
        return null;
    }

    public TaskOutputDto getByInitiatorReference(String initiatorReference) {
        return null;
    }

    public void processTaskData(TaskInputDto data) {
    }
}
