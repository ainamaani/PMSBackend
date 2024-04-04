package com.ainamaani.projects.pms.controllers;

import com.ainamaani.projects.pms.models.Project;
import com.ainamaani.projects.pms.models.enums.ProjectStatus;
import com.ainamaani.projects.pms.services.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.*;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private Validator validator;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody Project project, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }

        if (!isValidStatus(project.getStatus())) {
            return ResponseEntity.badRequest().body("Invalid status value. Allowed values are: COMPLETED, ONHOLD, CANCELLED, INPROGRESS");
        }

        try{
            Project newProject = projectService.createProject(project);
            if(newProject != null){
                System.out.println(newProject);
                return ResponseEntity.ok("Project created successfully");

            }else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Project save failed");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getLocalizedMessage());
        }
    }

    private boolean isValidStatus(ProjectStatus status) {
        return EnumSet.of(ProjectStatus.CANCELLED, ProjectStatus.ONHOLD, ProjectStatus.COMPLETED, ProjectStatus.INPROGRESS).contains(status);
    }
}
