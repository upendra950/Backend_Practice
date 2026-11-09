package com.example.taskmanagement.service;

import com.example.taskmanagement.dto.TaskRequest;
import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.model.TaskPriority;
import com.example.taskmanagement.model.TaskStatus;

import java.util.List;
import java.util.Map;

public interface TaskService {
    Task createTask(TaskRequest request);
    List<Task> getAllTasks();
    Task getTaskById(Long id);
    Task updateTask(Long id, TaskRequest request);
    void deleteTask(Long id);
    List<Task> searchTasks(String keyword);
    List<Task> getTasksByStatus(TaskStatus status);
    List<Task> getTasksByPriority(TaskPriority priority);
    Task updateStatus(Long id, TaskStatus status);
    Map<String, Long> getTaskSummary();
}
