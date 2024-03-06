package com.statvital.StatVital.services;

import com.statvital.StatVital.dtos.request.SendMailRequest;
import com.statvital.StatVital.dtos.response.SendMailResponse;

public interface MailService {
    SendMailResponse sendMail(SendMailRequest mailRequest);
}
