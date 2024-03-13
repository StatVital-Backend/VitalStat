package com.statvital.StatVital.dtos.request;

import lombok.Data;

@Data
public class SignUpMorgueRequest {
    private String facilityName;
    private String facilityLocation;
    private String sector;
    private String certificationNumber;
    private String email;
    private String phoneNumber;
    private String password;
}
