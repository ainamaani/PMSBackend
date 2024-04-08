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
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/add")
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
                return ResponseEntity.status(200).body(newTask);

            }else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Project save failed");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getLocalizedMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> handleFetchAllTasks (){
        try{
            List<Task> fetchedTasks = taskService.getTasks();
            if(fetchedTasks != null){
                return ResponseEntity.status(200).body(fetchedTasks);
            }else{
                return ResponseEntity.status(400).body("Failed to fetch the tasks");
            }
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getLocalizedMessage());
        }
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<?> handleFetchSingleTask(@PathVariable Long id){
        try{
            Task fetchedTask = taskService.getOneTask(id);
            if(fetchedTask != null){
                return ResponseEntity.status(200).body(fetchedTask);
            }else{
                return ResponseEntity.status(400).body("Failed to fetch the task");
            }
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getLocalizedMessage());
        }
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<?> handleDeleteTask(@PathVariable Long id){
        try{
            Task deletedTask = taskService.deleteTask(id);
            if(deletedTask != null){
                return ResponseEntity.status(200).body(deletedTask);
            }else{
                return ResponseEntity.status(400).body("Failed to delete the task");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getLocalizedMessage());
        }
    }
}
