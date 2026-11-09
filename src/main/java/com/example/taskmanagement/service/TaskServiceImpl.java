package com.example.taskmanagement.service;

import com.example.taskmanagement.dto.TaskRequest;
import com.example.taskmanagement.exception.TaskNotFoundException;
import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.model.TaskPriority;
import com.example.taskmanagement.model.TaskStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final List<Task> tasks = new ArrayList<>();
    private final AtomicLong nextId = new AtomicLong(1);

    public TaskServiceImpl() {
        addSample("Complete Java basics", "Revise classes, objects and methods",
                TaskStatus.IN_PROGRESS, TaskPriority.HIGH, LocalDate.now().plusDays(1));
        addSample("Practice Java Streams", "Try filter, map and collect",
                TaskStatus.TODO, TaskPriority.MEDIUM, LocalDate.now().plusDays(3));
        addSample("Build REST API", "Create GET and POST endpoints",
                TaskStatus.COMPLETED, TaskPriority.LOW, LocalDate.now().minusDays(1));
    }

    private void addSample(String title, String description, TaskStatus status,
                           TaskPriority priority, LocalDate dueDate) {
        tasks.add(new Task(nextId.getAndIncrement(), title, description, status, priority, dueDate));
    }

    @Override
    public Task createTask(TaskRequest request) {
        Task task = new Task(nextId.getAndIncrement(),
                request.getTitle().trim(),
                request.getDescription() == null ? "" : request.getDescription().trim(),
                request.getStatus(), request.getPriority(), request.getDueDate());
        tasks.add(task);
        return task;
    }

    @Override
    public List<Task> getAllTasks() {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getId))
                .collect(Collectors.toList());
    }

    @Override
    public Task getTaskById(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    @Override
    public Task updateTask(Long id, TaskRequest request) {
        Task task = getTaskById(id);
        task.setTitle(request.getTitle().trim());
        task.setDescription(request.getDescription() == null ? "" : request.getDescription().trim());
        task.setStatus(request.getStatus());
        task.setPriority(request.getPriority());
        task.setDueDate(request.getDueDate());
        return task;
    }

    @Override
    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        tasks.remove(task);
    }

    @Override
    public List<Task> searchTasks(String keyword) {
        String search = keyword.toLowerCase();
        return tasks.stream()
                .filter(task -> task.getTitle().toLowerCase().contains(search)
                        || task.getDescription().toLowerCase().contains(search))
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getTasksByStatus(TaskStatus status) {
        return tasks.stream()
                .filter(task -> task.getStatus() == status)
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getTasksByPriority(TaskPriority priority) {
        return tasks.stream()
                .filter(task -> task.getPriority() == priority)
                .collect(Collectors.toList());
    }

    @Override
    public Task updateStatus(Long id, TaskStatus status) {
        Task task = getTaskById(id);
        task.setStatus(status);
        return task;
    }

    @Override
    public Map<String, Long> getTaskSummary() {
        return tasks.stream().collect(Collectors.groupingBy(
                task -> task.getStatus().name(), Collectors.counting()));
    }
}
