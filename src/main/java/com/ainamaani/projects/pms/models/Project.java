package com.ainamaani.projects.pms.models;

import com.ainamaani.projects.pms.models.enums.ProjectPriorityLevel;
import com.ainamaani.projects.pms.models.enums.ProjectStatus;
import jakarta.persistence.*;
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
    private String projectName;
    private String projectDescription;
    private Date startDate;
    private Date endDate;
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;
    @Enumerated(EnumType.STRING)
    private ProjectPriorityLevel priority;
    private String projectManager;
    @ElementCollection
    private List<String> teamMembers;
    private String budgetAllocation;
    private String client;
    private File projectAttachments;
    private String additionalNotes;

}
