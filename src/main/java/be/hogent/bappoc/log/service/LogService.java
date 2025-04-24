package be.hogent.bappoc.log.service;

import be.hogent.bappoc.log.dto.ProcessInstanceInputDto;
import be.hogent.bappoc.log.dto.ProcessInstanceOutputDto;
import be.hogent.bappoc.log.entity.Activity;
import be.hogent.bappoc.log.entity.ActivityStatus;
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
    public ProcessInstanceOutputDto processMonitoringData(ProcessInstanceInputDto data) {
        if (isNotEmpty(data.getProcessInstanceReference())){
            return updateProcessExecutionLog(data);
        } else {
            return createNewProcessExecutionLog(data);
        }
        
    }
    private ProcessInstanceOutputDto createNewProcessExecutionLog(ProcessInstanceInputDto data) {
        log.info("Creating new Execution Log.");
        if (data.getActivities().size() != 1) {
            log.error("Data contained more than 1 initial activity.");
            throw new IllegalArgumentException("A new process may not have more than one initial activity.");}
        ProcessInstanceExecutionLog toPersist = logMapper.toEntity(data);
        log.info("Persisting Execution Log for Process Instance {}",toPersist.getProcessInstanceReference());
        repository.save(toPersist);
        generateTaskBasedOnActivity(toPersist,toPersist.getActivities().get(0));
        return logMapper.toOutputDto(toPersist);
    }
    private ProcessInstanceOutputDto updateProcessExecutionLog(ProcessInstanceInputDto data) {
        log.info("Updating Execution Log with Process Instance Reference {}.", data.getProcessInstanceReference());

        if (data.getActivityStatus() == ActivityStatus.START && data.getActivities().size() != 1) {
            log.error("Data contained more than one activity.");
            throw new IllegalArgumentException("A process may not be updated with more than one activity at a time.");
        }

        if (!repository.existsByProcessInstanceReference(data.getProcessInstanceReference())) {
            log.error("Process with Process Instance Reference {} does not exist.", data.getProcessInstanceReference());
            throw new IllegalArgumentException("Process to update does not exist.");
        }

        ProcessInstanceExecutionLog logToUpdate = repository.findByProcessInstanceReference(data.getProcessInstanceReference()).orElseThrow();
        if (logToUpdate.getActivityStatus() != ActivityStatus.START) {
            log.error("Process {} has already concluded. You can no longer alter it.", data.getProcessInstanceReference());
            throw new IllegalArgumentException("A process may not be updated if it has already concluded.");
        }

        if (data.getActivityStatus().equals(ActivityStatus.START)){

        Activity activityToAdd = activityMapper.toEntity(data.getActivities().get(0));

        if (activityToAdd.getActivityStatus() != ActivityStatus.START){
            logToUpdate.getActivities()
                    .stream()
                    .filter(activity -> activity.getActivityReference().equals(data.getActivities().get(0).getActivityReference()))
                    .findFirst()
                    .ifPresent(potentialPrecedingActivity -> activityToAdd.setActivityInstanceReference(potentialPrecedingActivity.getActivityInstanceReference()));
        }

        logToUpdate.getActivities().add(activityToAdd);
        generateTaskBasedOnActivity(logToUpdate, activityToAdd);
        } else {
            logToUpdate.setActivityStatus(data.getActivityStatus());
            logToUpdate.setProcessTimeStamp(data.getProcessTimeStamp());
        }
        repository.save(logToUpdate);
        return logMapper.toOutputDto(logToUpdate);
    }
    private void generateTaskBasedOnActivity(ProcessInstanceExecutionLog executionLog, Activity newActivity) {
        engine.generateTaskBasedOnActivity(executionLog,newActivity);
    }
}
