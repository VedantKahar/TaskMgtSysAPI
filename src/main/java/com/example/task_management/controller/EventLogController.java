package com.example.task_management.controller;

import com.example.task_management.model.EventLog;
import com.example.task_management.repository.EventLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event-logs")
@RequiredArgsConstructor
public class EventLogController {

    private final EventLogRepository eventLogRepository;

    @GetMapping
    public ResponseEntity<List<EventLog>> getAllLogs() {
        return ResponseEntity.ok(eventLogRepository.findAll());
    }
}
