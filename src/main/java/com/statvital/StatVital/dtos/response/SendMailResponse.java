package com.statvital.StatVital.dtos.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SendmailResponse {
    private int statusCode;
    private String messageId;
}
