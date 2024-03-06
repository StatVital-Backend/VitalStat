package com.statvital.StatVital.dtos.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegisterDeathResponse {
    private LocalDateTime dateRegistered;
    private String message;
    private String id;
}
