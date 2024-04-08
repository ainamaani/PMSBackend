package com.ainamaani.projects.pms.services;

import com.ainamaani.projects.pms.models.Task;

import java.util.List;

public interface TaskService {
    Task addTask (Task newProject);
    List<Task> getTasks ();
    Task getOneTask(Long id);
    Task deleteTask(Long id);
}
