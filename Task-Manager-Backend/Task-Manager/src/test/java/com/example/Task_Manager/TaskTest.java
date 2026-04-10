package com.example.Task_Manager;

import com.example.Task_Manager.Entity.Task;
import com.example.Task_Manager.Repository.TaskRepo;
import com.example.Task_Manager.Services.TaskService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    void testToggleTask() {

        TaskRepo repo = Mockito.mock(TaskRepo.class);
        TaskService service = new TaskService(repo);

        Task task = new Task();
        task.setId(1L);
        task.setCompleted(false);

        Mockito.when(repo.findById(1L)).thenReturn(Optional.of(task));
        Mockito.when(repo.save(task)).thenReturn(task);

        Task updated = service.updateTask(1L);

        assertTrue(updated.isCompleted());
    }
}