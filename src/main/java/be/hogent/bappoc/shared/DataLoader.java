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
        employeeRepository.save(Employee.builder().name("Balthasar Boma").numberOfTasks(0).maxAllowedTasks(8).build());
        employeeRepository.save(Employee.builder().name("Dimitri De Tremmerie").numberOfTasks(0).maxAllowedTasks(8).build());
        employeeRepository.save(Employee.builder().name("Fernand Costermans").numberOfTasks(0).maxAllowedTasks(8).build());
    }
}
