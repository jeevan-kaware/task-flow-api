package com.jeevan.taskflowapi.dto.request;

import com.jeevan.taskflowapi.enums.TaskStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateTaskStatusRequest {

    @NotNull(message = "Status is required")
    private TaskStatus status;

}