package be.hogent.bappoc.shared;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,String> {
    Employee findFirstByOrderByNumberOfTasksAsc();
}
