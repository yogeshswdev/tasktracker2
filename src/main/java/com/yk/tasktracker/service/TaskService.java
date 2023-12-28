package com.yk.tasktracker.service;

import java.util.List;

import com.yk.tasktracker.entity.Task;

public interface TaskService {
    List<Task> findAll();

    Task findById(int theId);

    void save(Task task);

    void deleteById(int id);

}
