package com.statvital.StatVital.dtos.response;

import lombok.Data;

@Data
public class SignInHospitalAdminResponse {
    private String facilityName;
    private String email;
    private String registerDate;
    private String message;
    private boolean isSuccessful;
}
