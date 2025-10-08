package com.kanbanTasks.kanbanTasksBackend.service;

import com.kanbanTasks.kanbanTasksBackend.model.Task;
import com.kanbanTasks.kanbanTasksBackend.repository.TaskRepository;
import com.kanbanTasks.kanbanTasksBackend.kafka.TaskProducer;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository repository;
    private final TaskProducer taskProducer;

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task createTask(Task task) {
        Task saved = repository.save(task);
        taskProducer.sendTaskEvent(saved);
        return saved;
    }

    public Task updateTask(Long id, Task updatedTask) {
        return repository.findById(id).map(task -> {
            task.setTitle(updatedTask.getTitle());
            task.setTag(updatedTask.getTag());
            task.setStatus(updatedTask.getStatus());
            task.setPriority(updatedTask.getPriority());
            task.setCreatedAt(updatedTask.getCreatedAt());
            Task saved = repository.save(task);
            taskProducer.sendTaskEvent(saved);
            return saved;
        }).orElseThrow(() -> new RuntimeException("Task not found: " + id));
    }

    public void deleteTask(Long id) {
        repository.deleteById(id);
    }
}
