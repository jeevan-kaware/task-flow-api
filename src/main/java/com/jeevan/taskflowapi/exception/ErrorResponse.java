package com.jeevan.taskflowapi.exception;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {

    private boolean success;

    private String message;

    private LocalDateTime timestamp;

}