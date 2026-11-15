package com.example.taskmanagement.service.impl;

import com.example.taskmanagement.entity.Task;
import com.example.taskmanagement.entity.TaskPriority;
import com.example.taskmanagement.entity.TaskStatus;
import com.example.taskmanagement.exception.TaskNotFoundException;
import com.example.taskmanagement.repository.TaskRepository;
import com.example.taskmanagement.service.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createTask(Task task) {
        task.setId(null);
        return taskRepository.save(task);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Task getTaskById(Long id) {
        return findTask(id);
    }

    @Override
    public Task updateTask(Long id, Task updatedTask) {
        Task existing = findTask(id);
        existing.setTitle(updatedTask.getTitle());
        existing.setDescription(updatedTask.getDescription());
        existing.setStatus(updatedTask.getStatus());
        existing.setPriority(updatedTask.getPriority());
        existing.setDueDate(updatedTask.getDueDate());
        return taskRepository.save(existing);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.delete(findTask(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> searchTasks(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            throw new IllegalArgumentException("Search keyword must not be blank");
        }
        return taskRepository.searchTasks(keyword.trim());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getTasksByPriority(TaskPriority priority) {
        return taskRepository.findByPriority(priority);
    }

    @Override
    public Task updateTaskStatus(Long id, TaskStatus status) {
        Task task = findTask(id);
        task.setStatus(status);
        return taskRepository.save(task);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Long> getTaskSummary() {
        Map<String, Long> summary = new LinkedHashMap<>();
        for (TaskStatus status : TaskStatus.values()) {
            summary.put(status.name(), 0L);
        }
        taskRepository.countTasksByStatus().forEach(row ->
                summary.put(((TaskStatus) row[0]).name(), (Long) row[1]));
        return summary;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Task> getTasksByStatusPaged(TaskStatus status, int page, int size) {
        if (page < 0 || size < 1 || size > 100) {
            throw new IllegalArgumentException("Page must be >= 0 and size must be between 1 and 100");
        }
        return taskRepository.findByStatus(status, PageRequest.of(page, size));
    }

    private Task findTask(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }
}
