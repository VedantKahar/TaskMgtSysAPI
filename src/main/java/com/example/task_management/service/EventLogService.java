package com.example.task_management.service;

import com.example.task_management.model.EventLog;
import com.example.task_management.repository.EventLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EventLogService {

    private final EventLogRepository eventLogRepository;

    @Transactional
    public void logEvent(String eventType, String details) {
        EventLog log = EventLog.builder()
                .eventType(eventType)
                .details(details)
                .timestamp(LocalDateTime.now())
                .build();
        eventLogRepository.save(log);
    }
}
