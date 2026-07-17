package com.jeevan.taskflowapi.dto.request;

import com.jeevan.taskflowapi.enums.Priority;
import com.jeevan.taskflowapi.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskRequest {


    @NotBlank(message = "Task title is required")
    private String title;


    private String description;


    @NotNull(message = "Priority is required")
    private Priority priority;


    private TaskStatus status;


    private LocalDate dueDate;


    @NotNull(message = "Project id is required")
    private Long projectId;


    @NotNull(message = "Assigned user id is required")
    private Long assignedUserId;

}