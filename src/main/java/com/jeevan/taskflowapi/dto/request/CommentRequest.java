package com.jeevan.taskflowapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentRequest {

    @NotBlank(message = "Comment cannot be empty")
    private String message;

    @NotNull(message = "Task id is required")
    private Long taskId;
}