package com.statvital.StatVital.dtos.response;

import lombok.Data;

@Data
public class DataResponse {
    private Long id;

    private String email;

    private String facilityName;
    private String facilityLocation;
}
