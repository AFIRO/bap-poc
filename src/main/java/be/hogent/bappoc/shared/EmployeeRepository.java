package be.hogent.bappoc.shared;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,String> {
    @Query("SELECT e from Employee e where e.numberOfTasks < e.maxAllowedTasks order by e.numberOfTasks asc")
    List<Employee> getEmployeesWithRoomForTasks();
}
