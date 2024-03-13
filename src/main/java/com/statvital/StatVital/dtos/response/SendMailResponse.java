package com.statvital.StatVital.dtos.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class SendMailResponse {
    private int statusCode;
    private String messageId;
    private LocalDate dateCreated;
}
