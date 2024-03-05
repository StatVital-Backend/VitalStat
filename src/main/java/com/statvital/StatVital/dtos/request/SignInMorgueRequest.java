package com.statvital.StatVital.dtos.request;


import lombok.Data;

@Data
public class SignInMorgueRequest {
    private String email;
    private String password;
    private String facilityName;
}
