package be.hogent.bappoc.log.controller;

import be.hogent.bappoc.log.dto.ProcessInstanceInputDto;
import be.hogent.bappoc.log.dto.ProcessInstanceOutputDto;
import be.hogent.bappoc.log.service.LogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
public class MonitoringController {
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
        log.info("Monotoring posted for process instance {}", data.getProcessInstanceReference());
        try {
            service.processMonitoringData(data);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

}
