package com.ainamaani.projects.pms.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
    private String status;
    private String priority;
    private String projectManager;
    private List<String> teamMembers;
    private String budgetAllocation;
    private String client;
    private File projectAttachments;
    private String additionalNotes;

}
