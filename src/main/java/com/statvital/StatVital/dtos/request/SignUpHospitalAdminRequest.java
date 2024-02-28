package com.statvital.StatVital.dtos.request;


import lombok.Data;

@Data
public class SignInHospitalAdminRequest {
    private String FacilityName;
    private String FacilityUID;
    private String email;
    private String password;
    private String sector;

}
