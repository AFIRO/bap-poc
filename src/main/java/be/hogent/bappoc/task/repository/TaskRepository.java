package be.hogent.bappoc.task.repository;

import be.hogent.bappoc.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task,String> {
    Optional<Task> getTaskByTaskInstanceReference(String taskInstanceReference);
    Optional<Task> getTaskByExecutorReference(String executorReference);
    Optional<Task> getTaskByInitiatorReference(String initiatorReference);
    boolean existsByTaskInstanceReference(String taskInstanceReference);

}
