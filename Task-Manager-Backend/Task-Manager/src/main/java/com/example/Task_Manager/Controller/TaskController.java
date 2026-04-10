package com.example.Task_Manager.Controller;

import com.example.Task_Manager.Entity.Task;
import com.example.Task_Manager.Services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:5173")
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service){
        this.service = service;
    }
    @GetMapping
    public List<Task> getTasks(){
        return service.getAllTasks();
    }
    @PostMapping
    public Task createTask(@RequestBody Task task){
        return service.createTask(task);
    }
    @PatchMapping("/{id}")
    public Task updateTask(@PathVariable Long id){
        return service.updateTask(id);
    }
    @DeleteMapping("/{id}")
    public void  deleteTask(@PathVariable Long id){
        service.deleteTask(id);
    }
    @PutMapping("/{id}")
    public Task updateTitle(@PathVariable Long id, @RequestBody Task updatedTask) {
        return service.updateTitle(id, updatedTask);
    }
}
