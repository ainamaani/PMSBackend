package com.ainamaani.projects.pms.controllers;

import com.ainamaani.projects.pms.models.Project;
import com.ainamaani.projects.pms.models.Task;
import com.ainamaani.projects.pms.repositories.TaskRepository;
import com.ainamaani.projects.pms.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PutMapping("/add")
    public ResponseEntity<?> handleAddTask(@Valid @RequestBody Task task, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }


        try{
            Task newTask = taskService.addTask(task);
            if(newTask != null){
                return ResponseEntity.ok("Project created successfully");

            }else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Project save failed");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getLocalizedMessage());
        }
    }
}
