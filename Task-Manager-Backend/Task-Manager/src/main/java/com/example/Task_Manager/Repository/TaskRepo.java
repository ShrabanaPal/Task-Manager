package com.example.Task_Manager.Repository;

import com.example.Task_Manager.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task , Long>{

}
