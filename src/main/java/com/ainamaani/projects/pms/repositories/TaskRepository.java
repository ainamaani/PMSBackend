package com.ainamaani.projects.pms.repositories;

import com.ainamaani.projects.pms.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
