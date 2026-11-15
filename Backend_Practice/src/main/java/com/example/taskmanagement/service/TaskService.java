package com.example.taskmanagement.service;

import com.example.taskmanagement.entity.Task;
import com.example.taskmanagement.entity.TaskPriority;
import com.example.taskmanagement.entity.TaskStatus;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface TaskService {
    Task createTask(Task task);
    List<Task> getAllTasks();
    Task getTaskById(Long id);
    Task updateTask(Long id, Task task);
    void deleteTask(Long id);
    List<Task> searchTasks(String keyword);
    List<Task> getTasksByStatus(TaskStatus status);
    List<Task> getTasksByPriority(TaskPriority priority);
    Task updateTaskStatus(Long id, TaskStatus status);
    Map<String, Long> getTaskSummary();
    Page<Task> getTasksByStatusPaged(TaskStatus status, int page, int size);
}
