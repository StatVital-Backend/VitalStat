package com.statvital.StatVital.dtos.response;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LogInAdminResponse {
    private String logInDate;
    private String message;
    private boolean isLoggedIn;
    private String token;
}
