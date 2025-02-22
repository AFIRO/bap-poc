package be.hogent.bappoc.log.service;

import be.hogent.bappoc.log.dto.ProcessInstanceInputDto;
import be.hogent.bappoc.log.dto.ProcessInstanceOutputDto;
import be.hogent.bappoc.log.entity.ProcessInstanceExecutionLog;
import be.hogent.bappoc.log.mapper.LogMapper;
import be.hogent.bappoc.log.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class LogService {
    private final LogRepository repository;
    private final LogMapper mapper;

    public List<ProcessInstanceOutputDto> getAll() {
        log.info("Getting data from repo");
        List<ProcessInstanceExecutionLog> data = repository.findAll();
        return data.stream().map(mapper::toOutputDto).toList();
    }

    public ProcessInstanceOutputDto getByProcessInstanceReference(String processInstanceReference) {
        log.info("Getting data from repo");
        ProcessInstanceExecutionLog data = repository.findByProcessInstanceReference(processInstanceReference).orElseThrow();
        return mapper.toOutputDto(data);
    }

    public ProcessInstanceOutputDto getByInitiatorReference(String initiatorReference) {
        log.info("Getting data from repo");
        ProcessInstanceExecutionLog data = repository.findByInitiatorReference(initiatorReference).orElseThrow();
        return mapper.toOutputDto(data);
    }

    public void processMonitoringData(ProcessInstanceInputDto data) {
    }
}
