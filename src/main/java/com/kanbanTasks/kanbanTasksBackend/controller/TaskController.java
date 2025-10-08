package com.kanbanTasks.kanbanTasksBackend.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.kanbanTasks.kanbanTasksBackend.model.Task;
import com.kanbanTasks.kanbanTasksBackend.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:5173") // allow frontend dev server
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return service.getAllTasks();
    }

    @PostMapping
    public Task createTask(@Valid @RequestBody Task task) {
        return service.createTask(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @Valid@RequestBody Task updatedTask) {
        return service.updateTask(id, updatedTask);
    }


    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        service.deleteTask(id);
    }
}