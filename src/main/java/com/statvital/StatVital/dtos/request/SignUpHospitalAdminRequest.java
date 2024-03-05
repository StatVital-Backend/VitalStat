package com.statvital.StatVital.dtos.request;


import lombok.Data;

@Data
public class SignUpHospitalAdminRequest {
    private String facilityName;
    private String facilityUID;
    private String email;
    private String password;
    private String sector;

}
