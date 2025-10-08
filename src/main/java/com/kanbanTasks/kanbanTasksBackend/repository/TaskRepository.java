package com.kanbanTasks.kanbanTasksBackend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.kanbanTasks.kanbanTasksBackend.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}