package be.hogent.bappoc.log.repository;

import be.hogent.bappoc.log.entity.ProcessInstanceExecutionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface LogRepository extends JpaRepository<ProcessInstanceExecutionLog, String> {
    Optional<ProcessInstanceExecutionLog> findByProcessInstanceReference(String processInstanceReference);
    Optional<ProcessInstanceExecutionLog> findByInitiatorReference(String initiatorReference);
    boolean existsByProcessInstanceReference(String processInstanceReference);
}
