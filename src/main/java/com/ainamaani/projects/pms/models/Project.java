package com.ainamaani.projects.pms.models;

import com.ainamaani.projects.pms.models.enums.ProjectPriorityLevel;
import com.ainamaani.projects.pms.models.enums.ProjectStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Projects")
@Getter
@Setter
public class Project extends BaseEntity{
    @NotBlank(message = "Project name is required")
    private String projectName;
    @NotBlank(message = "Project description is required")
    private String projectDescription;
    @NotNull(message = "Project start date is required")
    private Date startDate;
    @NotNull(message = "Project end date is required")
    private Date endDate;
    @NotBlank(message = "Project status is required")
    private String status;
    @NotBlank(message = "Project priority is required")
    private String priority;
    @NotBlank(message = "Project manager name is required")
    private String projectManager;
    @NotEmpty(message = "Project team members is required")
    private List<String> teamMembers;
    @NotBlank(message = "Project budget allocation is required")
    private String budgetAllocation;
    @NotBlank(message = "Project client is required")
    private String client;
    @NotBlank(message = "Additional notes about the project are required")
    private String additionalNotes;

}
