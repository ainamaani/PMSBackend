package com.ainamaani.projects.pms.services.impl;

import com.ainamaani.projects.pms.models.Project;
import com.ainamaani.projects.pms.repositories.ProjectRepository;
import com.ainamaani.projects.pms.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Override
    public Project createProject(Project project){
        return projectRepository.save(project);
    }

    @Override
    public List<Project> fetchProjects(){
        return projectRepository.findAll();
    }

    @Override
    public Project fetchSingleProject(Long id){
        Optional<Project> project = projectRepository.findById(id);

        if(project.isPresent()){
            return project.get();
        }else{
            throw new RuntimeException("Project with such an id doesn't exist");
        }
    }
    @Override
    public Project deleteProject(Long id){
        Optional<Project> projectToBeDeleted = projectRepository.findById(id);

        if(projectToBeDeleted.isPresent()){
            projectRepository.deleteById(id);
            return projectToBeDeleted.get();
        }else {
            throw new RuntimeException("The project to be deleted hasn't been found");
        }

    }

    @Override
    public Project updateProject(Long id, Project project){
        Optional<Project> existingProject = projectRepository.findById(id);

        if(existingProject.isPresent()){
            Project projectToUpdate = existingProject.get();

            // update the fields
            projectToUpdate.setProjectName(project.getProjectName());
            projectToUpdate.setProjectDescription(project.getProjectDescription());
            projectToUpdate.setProjectManager(project.getProjectManager());
            projectToUpdate.setClient(project.getClient());
            projectToUpdate.setPriority(project.getPriority());
            projectToUpdate.setStatus(project.getStatus());
            projectToUpdate.setEndDate(project.getEndDate());
            projectToUpdate.setStartDate(project.getStartDate());
            projectToUpdate.setAdditionalNotes(project.getAdditionalNotes());
            projectToUpdate.setBudgetAllocation(project.getBudgetAllocation());
            projectToUpdate.setTeamMembers(project.getTeamMembers());

            return projectRepository.save(projectToUpdate);
        }else{
            throw new RuntimeException("Failed to find the project with id " + id + " to update");
        }
    }

    @Override
    public Project changeProjectStatus(Long id, String newProject){
        Optional<Project> projectToChangeStatusExists = projectRepository.findById(id);
        if(projectToChangeStatusExists.isPresent()){
            Project projectToChangeStatus = projectToChangeStatusExists.get();

            projectToChangeStatus.setStatus(newProject);
            return projectRepository.save(projectToChangeStatus);
        }else{
            throw new RuntimeException("Failed to find the project to change status");
        }
    }
}
