package com.ainamaani.projects.pms.services.impl;

import com.ainamaani.projects.pms.models.Project;
import com.ainamaani.projects.pms.repositories.ProjectRepository;
import com.ainamaani.projects.pms.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Override
    public Project createProject(Project project){
        return projectRepository.save(project);
    }
}
