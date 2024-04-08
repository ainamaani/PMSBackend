package com.ainamaani.projects.pms.services.impl;

import com.ainamaani.projects.pms.models.Task;
import com.ainamaani.projects.pms.repositories.TaskRepository;
import com.ainamaani.projects.pms.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task addTask(Task task){
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getTasks(){
        return taskRepository.findAll();
    }

    @Override
    public Task getOneTask(Long id){
        try{
            Optional<Task> taskExists = taskRepository.findById(id);
            if(taskExists.isPresent()){
                return taskExists.get();
            }else{
                throw new RuntimeException("The task with that id doesn't exist");
            }
        }catch(Exception e){
            throw new RuntimeException("Failed to fetch the task with that id");
        }
    }

    @Override
    public Task deleteTask(Long id){
        try{
            Optional<Task> taskExists = taskRepository.findById(id);
            if(taskExists.isPresent()){
                taskRepository.deleteById(id);
                return taskExists.get();
            }else{
                throw new RuntimeException("The task with that id doesn't exist");
            }
        }catch(Exception e){
            throw new RuntimeException("Failed to delete task with that id");
        }
    }
}
