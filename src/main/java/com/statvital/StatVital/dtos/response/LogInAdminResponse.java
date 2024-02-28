package com.statvital.StatVital.dtos.response;


import lombok.Data;

@Data
public class LogInAdminResponse {
    private String logInDate;
    private String message;
    private String isLoggedIn;
}
