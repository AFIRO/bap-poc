package be.hogent.bappoc.task.controller;

import be.hogent.bappoc.log.dto.ProcessInstanceInputDto;
import be.hogent.bappoc.log.dto.ProcessInstanceOutputDto;
import be.hogent.bappoc.task.dto.TaskInputDto;
import be.hogent.bappoc.task.dto.TaskOutputDto;
import be.hogent.bappoc.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Log4j2
@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskService service;

    @GetMapping("/")
    public ResponseEntity<List<TaskOutputDto>> getAll() {
        log.info("Get all tasks called");
        try {
            return  ResponseEntity.ok().body(service.getAll());
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/ByTaskInstance/{id}")
    public ResponseEntity<TaskOutputDto> getByTaskInstanceReference(@PathVariable("id") String taskInstanceReference){
        log.info("Get by task instance reference {} called", taskInstanceReference);
        try {
            return ResponseEntity.ok().body(service.getByTaskInstanceReference(taskInstanceReference));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/ByExecutor/{id}")
    public ResponseEntity<TaskOutputDto> getByExecutorReference(@PathVariable("id") String executorReference){
        log.info("Get by executor reference {} called", executorReference);
        try {
            return ResponseEntity.ok().body(service.getByExecutorReference(executorReference));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/ByInitiator/{id}")
    public ResponseEntity<TaskOutputDto> getByInitiatorReference(@PathVariable("id") String initiatorReference){
        log.info("Get by initiator reference {} called", initiatorReference);
        try {
            return ResponseEntity.ok().body(service.getByInitiatorReference(initiatorReference));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity postMonitoring(@RequestBody TaskInputDto data) {
        log.info("Task data posted for task instance {}", data.getTaskInstanceReference());
        try {
            service.processTaskData(data);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }
}
