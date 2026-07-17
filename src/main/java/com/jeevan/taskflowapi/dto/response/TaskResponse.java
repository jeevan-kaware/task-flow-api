package com.jeevan.taskflowapi.dto.response;

import com.jeevan.taskflowapi.enums.Priority;
import com.jeevan.taskflowapi.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {

    private Long id;

    private String title;

    private String description;

    private Priority priority;

    private TaskStatus status;

    private LocalDate dueDate;

    private String assignedTo;

    private String projectName;

}