package com.statvital.StatVital.dtos.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class SignInHospitalAdminResponse {
    private String facilityName;
    private String email;
    private LocalDate registerDate;
    private String message;
    private String token;
}
