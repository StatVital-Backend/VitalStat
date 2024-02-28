package com.statvital.StatVital.dtos.response;

import lombok.Data;

@Data
public class SignInHospitalAdminResponse {
    private String facilityName;
    private String username;
    private String registerDate;
    private String message;
}
