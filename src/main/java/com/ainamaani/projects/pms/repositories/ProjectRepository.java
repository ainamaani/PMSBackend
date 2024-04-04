package com.ainamaani.projects.pms.repositories;

import com.ainamaani.projects.pms.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
