package be.hogent.bappoc.log.service;

import be.hogent.bappoc.log.dto.ProcessInstanceInputDto;
import be.hogent.bappoc.log.dto.ProcessInstanceOutputDto;
import be.hogent.bappoc.log.entity.Activity;
import be.hogent.bappoc.log.entity.ProcessInstanceExecutionLog;
import be.hogent.bappoc.log.mapper.ActivityMapper;
import be.hogent.bappoc.log.mapper.LogMapper;
import be.hogent.bappoc.log.repository.LogRepository;
import be.hogent.bappoc.shared.ExecutionEngine;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

import static org.apache.logging.log4j.util.Strings.isNotEmpty;

@Service
@RequiredArgsConstructor
@Log4j2
public class LogService {
    private final LogRepository repository;
    private final ExecutionEngine engine;
    private final LogMapper logMapper;
    private final ActivityMapper activityMapper;
    public List<ProcessInstanceOutputDto> getAll() {
        log.info("Getting data from repo");
        List<ProcessInstanceExecutionLog> data = repository.findAll();
        return data.stream()
                .map(logMapper::toOutputDto)
                .sorted(Comparator.comparing(ProcessInstanceOutputDto::getProcessTimeStamp))
                .toList();
    }
    public ProcessInstanceOutputDto getByProcessInstanceReference(String processInstanceReference) {
        log.info("Getting data from repo");
        ProcessInstanceExecutionLog data = repository.findByProcessInstanceReference(processInstanceReference).orElseThrow();
        return logMapper.toOutputDto(data);
    }
    public ProcessInstanceOutputDto getByInitiatorReference(String initiatorReference) {
        log.info("Getting data from repo");
        ProcessInstanceExecutionLog data = repository.findByInitiatorReference(initiatorReference).orElseThrow();
        return logMapper.toOutputDto(data);
    }
    public void processMonitoringData(ProcessInstanceInputDto data) {
        if (isNotEmpty(data.getProcessInstanceReference())){
            updateProcessExecutionLog(data);
        } else {
            createNewProcessExecutionLog(data);
        }
        
    }
    private void createNewProcessExecutionLog(ProcessInstanceInputDto data) {
        log.info("Creating new Execution Log.");
        if (data.getActivities().size() != 1) {
            log.error("Data contained more than 1 initial activity.");
            throw new IllegalArgumentException("A new process may not have more than one initial activity.");}
        ProcessInstanceExecutionLog toPersist = logMapper.toEntity(data);
        log.info("Persisting Execution Log for Process Instance {}",toPersist.getProcessInstanceReference());
        repository.save(toPersist);
        generateTaskBasedOnActivity(toPersist.getProcessInstanceReference(),toPersist.getActivities().get(0).getActivityReference());
    }
    private void updateProcessExecutionLog(ProcessInstanceInputDto data) {
        log.info("Updating Execution Log with Process Instance Reference {}.",data.getProcessInstanceReference());

        if (data.getActivities().size() != 1) {
            log.error("Data contained more than one activity.");
            throw new IllegalArgumentException("A process may not be updated with more than one activity at a time.");}

        if (!repository.existsByProcessInstanceReference(data.getProcessInstanceReference())){
            log.error("Process with Process Instance Reference {} does not exist.", data.getProcessInstanceReference());
            throw new IllegalArgumentException("Process to update does not exist.");}

        ProcessInstanceExecutionLog logToUpdate = repository.findByProcessInstanceReference(data.getProcessInstanceReference()).orElseThrow();
        Activity activityToAdd = activityMapper.toEntity(data.getActivities().get(0));
        logToUpdate.getActivities().add(activityToAdd);
        repository.save(logToUpdate);

        generateTaskBasedOnActivity(logToUpdate.getProcessInstanceReference(), activityToAdd.getActivityReference());
    }
    private void generateTaskBasedOnActivity(String processInstanceReference, String activityReference) {
        engine.generateTaskBasedOnActivity(processInstanceReference,activityReference);
    }
}
