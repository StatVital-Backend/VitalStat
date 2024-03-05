package com.statvital.StatVital.dtos.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeleteChildReq {
    private String childName;
    private LocalDateTime dateCreated;
    private String sex;
}
