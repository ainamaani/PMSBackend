package com.ainamaani.projects.pms.services.impl;

import com.ainamaani.projects.pms.models.Task;
import com.ainamaani.projects.pms.repositories.TaskRepository;
import com.ainamaani.projects.pms.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task addTask(Task task){
        return taskRepository.save(task);
    }
}
