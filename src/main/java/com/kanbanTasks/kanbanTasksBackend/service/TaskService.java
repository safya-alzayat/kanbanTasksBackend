package com.kanbanTasks.kanbanTasksBackend.service;

import com.kanbanTasks.kanbanTasksBackend.model.Task;
import com.kanbanTasks.kanbanTasksBackend.repository.TaskRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository repository;

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task createTask(Task task) {
        return repository.save(task);
    }

    public Task updateTask(Long id, Task updatedTask) {
        return repository.findById(id).map(task -> {
            task.setTitle(updatedTask.getTitle());
            task.setTag(updatedTask.getTag());
            task.setStatus(updatedTask.getStatus());
            task.setPriority(updatedTask.getPriority());
            task.setCreatedAt(updatedTask.getCreatedAt());
            return repository.save(task);
        }).orElseThrow(() -> new RuntimeException("Task not found: " + id));
    }

    public void deleteTask(Long id) {
        repository.deleteById(id);
    }
}
