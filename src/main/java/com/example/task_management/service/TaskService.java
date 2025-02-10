package com.example.task_management.service;

import com.example.task_management.model.Task;
import com.example.task_management.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;
    private final EventLogService eventLogService; // Inject EventLogService

    // returns a simple list-when pagination is not needed
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // returns paginated results
    public Page<Task> getAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    @Transactional
    public Task createTask(Task task) {
        Task savedTask = taskRepository.save(task);
        eventLogService.logEvent("TASK_CREATED", "Task created with ID: " + savedTask.getId());
        return savedTask;
    }

    @Transactional
    public Optional<Task> updateTask(Long id, Task updatedTask) {
        return taskRepository.findById(id).map(existingTask -> {
            if (updatedTask.getTitle() != null) {
                existingTask.setTitle(updatedTask.getTitle());
            }
            if (updatedTask.getDescription() != null) {
                existingTask.setDescription(updatedTask.getDescription());
            }
            if (updatedTask.getStatus() != null) {
                existingTask.setStatus(updatedTask.getStatus());
            }
            Task savedTask = taskRepository.save(existingTask);
            eventLogService.logEvent("TASK_UPDATED", "Task with ID: " + savedTask.getId() + " was updated.");
            return savedTask;
        });
    }

    @Transactional
    public void deleteTask(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            eventLogService.logEvent("TASK_DELETED", "Task with ID: " + id + " was deleted.");
        }
    }
}
