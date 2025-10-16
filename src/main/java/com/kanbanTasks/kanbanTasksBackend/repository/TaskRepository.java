package com.kanbanTasks.kanbanTasksBackend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.kanbanTasks.kanbanTasksBackend.model.Task;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatusOrderByPriorityDesc(String status);
}