package com.ainamaani.projects.pms.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="Tasks")
@Setter
@Getter
public class Task extends BaseEntity {
    @NotNull(message = "Project id is required")
    private Long projectId;
    @NotBlank(message = "Task name is required")
    private String taskName;
    @NotBlank(message = "Task description is required")
    private String description;
    @NotBlank(message = "Task status is required")
    private String status;
    @NotBlank(message = "Task priority is required")
    private String priority;
    @NotBlank(message = "Person assigned the task is required")
    private String assignedTo;
    @NotNull(message = "The task due date is required")
    private Date dueDate;
    @NotBlank(message = "Task additional notes are required")
    private String additionalNotes;
    @NotBlank(message = "Task timeline estimation is required")
    private String timeEstimation;

}
