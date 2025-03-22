package be.hogent.bappoc.log.controller;

import be.hogent.bappoc.log.dto.ProcessInstanceInputDto;
import be.hogent.bappoc.log.dto.ProcessInstanceOutputDto;
import be.hogent.bappoc.log.service.LogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.apache.logging.log4j.util.Strings.isNotEmpty;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/monitoring")
public class LogController {
    private final LogService service;
    @GetMapping("/")
    public ResponseEntity<List<ProcessInstanceOutputDto>> getAll() {
        log.info("Get all logs called");
        try {
            return  ResponseEntity.ok().body(service.getAll());
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/ByProcessInstance/{id}")
    public ResponseEntity<ProcessInstanceOutputDto> getByProcessInstanceReference(@PathVariable("id") String processInstanceReference){
        log.info("Get by process instance reference {} called", processInstanceReference);
        try {
            return ResponseEntity.ok().body(service.getByProcessInstanceReference(processInstanceReference));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/ByInitiator/{id}")
    public ResponseEntity<ProcessInstanceOutputDto> getByInitiatorReference(@PathVariable("id") String initiatorReference){
        log.info("Get by initiator reference {} called", initiatorReference);
        try {
            return ResponseEntity.ok().body(service.getByInitiatorReference(initiatorReference));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity postMonitoring(@RequestBody ProcessInstanceInputDto data) {
        log.info("Monitoring posted for process instance {}", isNotEmpty(data.getProcessInstanceReference())? data.getProcessInstanceReference() : "NEW");
        try {
            return ResponseEntity.ok().body(service.processMonitoringData(data));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
