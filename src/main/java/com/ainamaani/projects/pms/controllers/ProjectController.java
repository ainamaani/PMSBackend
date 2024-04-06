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
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private Validator validator;

    @PostMapping("/create")
    public ResponseEntity<?> handleCreateProject(@Valid @RequestBody Project project, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }

//        if (!isValidStatus(project.getStatus())) {
//            return ResponseEntity.badRequest().body("Invalid status value. Allowed values are: COMPLETED, ONHOLD, CANCELLED, INPROGRESS");
//        }

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

    @GetMapping("/")
    public List<Project> handleGetProjects(){
        return projectService.fetchProjects();
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<?> handleFetchSingleProject(@PathVariable Long id){
        try{
            Project project = projectService.fetchSingleProject(id);
            if(project != null){
                return ResponseEntity.status(200).body(project);
            }else{
                return ResponseEntity.status(400).body("Failed to fetch the project");
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getLocalizedMessage());
        }
    }

    @DeleteMapping("/project/{id}")
    public ResponseEntity<?> handleDeleteProject(@PathVariable Long id){
        System.out.println(id);
        try{
            Project deletedProject = projectService.deleteProject(id);
            if(deletedProject != null){
                return ResponseEntity.status(200).body(deletedProject);
            }else{
                return ResponseEntity.status(400).body("Failed to delete the project");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getLocalizedMessage());
        }
    }

    @PutMapping("/project/{id}")
    public ResponseEntity<?> handleUpdateProject(@PathVariable Long id, @RequestBody Project project){
        try{
            Project updatedProjectDetails = projectService.updateProject(id, project);
            if(updatedProjectDetails != null){
                return ResponseEntity.status(200).body(updatedProjectDetails);
            }else{
                return ResponseEntity.status(400).body("Failed to update the project details");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getLocalizedMessage());
        }
    }

    @PostMapping("/project/{id}/changestatus")
    public ResponseEntity<?> handleChangeProjectStatus(@PathVariable Long id, @RequestBody String newProjectStatus){
        try{
            Project updatedStatusProject = projectService.changeProjectStatus(id, newProjectStatus);
            if(updatedStatusProject != null){
               return ResponseEntity.status(200).body(updatedStatusProject);
            }else{
                return ResponseEntity.status(400).body("Failed to update the status of the project");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getLocalizedMessage());
        }
    }
}
