package com.statvital.StatVital.dtos.request;


import lombok.Data;

@Data
public class SignUpHospitalAdminRequest {
    private String facilityName;
    private String facilityLocation;
    private String email;
    private String password;
    private String sector;
    private String phoneNumber;


}
