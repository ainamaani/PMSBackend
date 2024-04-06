package com.ainamaani.projects.pms.services;

import com.ainamaani.projects.pms.models.Project;

import java.util.List;

public interface ProjectService {
    Project createProject( Project project );
    List<Project> fetchProjects();
    Project deleteProject(Long id);
    Project fetchSingleProject(Long id);
    Project updateProject(Long id, Project project);
    Project changeProjectStatus(Long id, String newStatus);

}
