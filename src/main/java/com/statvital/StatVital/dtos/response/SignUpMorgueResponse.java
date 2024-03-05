package com.statvital.StatVital.dtos.response;

import lombok.Data;

@Data
public class SignUpMorgueResponse {
    private String facilityName;
//    private String username;
    private String email;
    private String registerDate;
    private String message;
}
