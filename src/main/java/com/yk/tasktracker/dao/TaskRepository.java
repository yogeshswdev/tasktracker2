package com.yk.tasktracker.dao;

import com.yk.tasktracker.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {

}
