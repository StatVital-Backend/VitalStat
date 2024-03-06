package com.statvital.StatVital.dtos.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeathReq {
    private String receiver;
    private String deceasedName;
    private LocalDateTime dateTime;
    private String causeOfDeath;
    private String broughtBy;
    private String placeOfDeath;
    private String age;
    private String gender;
    private String status;
    private String occupation;

}
