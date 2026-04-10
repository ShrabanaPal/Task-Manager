package com.example.Task_Manager.Services;

import com.example.Task_Manager.Entity.Task;
import com.example.Task_Manager.Repository.TaskRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepo repo;

    public TaskService(TaskRepo repo) {
        this.repo = repo;
    }

    public List<Task> getAllTasks() {
        return repo.findAll();
    }

    public Task createTask(Task task) {
        if(task.getTitle()==null || task.getTitle().isEmpty()){
            throw new RuntimeException("Task title cannot be empty !");
        }
        task.setCreatedAt(LocalDateTime.now());
        task.setCompleted(false);
        return repo.save(task);
    }

    public Task updateTask(Long id) {
        Task task = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setCompleted(!task.isCompleted());
        return repo.save(task);
    }

    public void deleteTask(Long id) {
        repo.deleteById(id);
    }
    public Task updateTitle(Long id , Task updatedTask){
        Task task = repo.findById(id)
                .orElseThrow(()-> new RuntimeException("Task not Found !"));
        task.setTitle(updatedTask.getTitle());
        return repo.save(task);
    }
}
