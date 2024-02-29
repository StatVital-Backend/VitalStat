package com.statvital.StatVital.dtos.request;


import lombok.Data;

@Data
public class SignInHospitalRequest {
    private String email;
    private String password;
    private String facilityName;
}
