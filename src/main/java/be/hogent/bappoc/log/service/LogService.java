package be.hogent.bappoc.log.service;

import be.hogent.bappoc.log.dto.ProcessInstanceInputDto;
import be.hogent.bappoc.log.dto.ProcessInstanceOutputDto;
import be.hogent.bappoc.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class LogService {
    private final LogRepository repository;

    public List<ProcessInstanceOutputDto> getAll() {
        return null;
    }

    public ProcessInstanceOutputDto getByProcessInstanceReference(String processInstanceReference) {
        return null;
    }

    public ProcessInstanceOutputDto getByInitiatorReference(String initiatorReference) {
        return null;
    }

    public void processMonitoringData(ProcessInstanceInputDto data) {
    }
}
