package com.statvital.StatVital.dtos.response;


import lombok.Data;

@Data
public class LogInMorgueResponse {
    private String logInDate;
    private String message;
    private boolean isLoggedIn;

}
