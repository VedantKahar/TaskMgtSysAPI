package com.example.task_management.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventType; // e.g., "TASK_CREATED", "TASK_UPDATED"
    private String details;   // e.g., "Task with ID 1 was created."
    private LocalDateTime timestamp; // Event time
}
