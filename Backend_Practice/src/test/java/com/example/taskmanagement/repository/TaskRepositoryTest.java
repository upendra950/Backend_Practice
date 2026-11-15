package com.example.taskmanagement.repository;

import com.example.taskmanagement.entity.Task;
import com.example.taskmanagement.entity.TaskPriority;
import com.example.taskmanagement.entity.TaskStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TaskRepositoryTest {
    @Autowired
    private TaskRepository taskRepository;

    @Test
    void shouldPersistAndSearchTask() {
        taskRepository.save(new Task("Learn JPA", "Practice repositories",
                TaskStatus.TODO, TaskPriority.HIGH, null));

        List<Task> results = taskRepository.searchTasks("jpa");

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getTitle()).isEqualTo("Learn JPA");
    }
}
