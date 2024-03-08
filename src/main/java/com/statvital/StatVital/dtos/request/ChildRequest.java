package com.statvital.StatVital.dtos.request;

import lombok.Data;
import org.springframework.format.datetime.DateFormatter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ChildRequest {
    private String id;
    private String name;
    private String dob;
    private String nurseName;
    private String fatherName;
    private String motherName;
    private String childName;
    private LocalDate dateCreated;
    private String stateOfOrigin;
    private String sex;
}
