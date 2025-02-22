package be.hogent.bappoc.shared;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader {
    private final EmployeeRepository employeeRepository;

    @PostConstruct
    private void LoadData(){
        seedEmployees();
    }
    private void seedEmployees() {
        int NUMBER_OF_EMPLOYEES_TO_SEED = 3;
        for (int counter = 0; counter < NUMBER_OF_EMPLOYEES_TO_SEED; counter++) {
            employeeRepository.save(Employee.builder().numberOfTasks(0).build());
        }
    }
}
