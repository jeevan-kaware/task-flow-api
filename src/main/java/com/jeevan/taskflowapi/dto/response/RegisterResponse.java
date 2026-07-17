package com.jeevan.taskflowapi.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterResponse {

    private Long id;
    private String fullName;
    private String email;
    private String message;

}