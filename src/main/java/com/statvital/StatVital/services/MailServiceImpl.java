package com.statvital.StatVital.services;

import com.statvital.StatVital.config.AppConfig;
import com.statvital.StatVital.dtos.request.SendMailRequest;
import com.statvital.StatVital.dtos.response.SendMailResponse;
import com.statvital.StatVital.exceptions.MailServiceException;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService{
    private final RestTemplate restTemplate;
    private final AppConfig appConfig;

    @Override
    public SendMailResponse sendMail(SendMailRequest mailRequest) {
        try {
            HttpHeaders headers = addRequestHeaders();
            RequestEntity<SendMailRequest> requestEntity = new RequestEntity<>(mailRequest, headers, POST, URI.create(appConfig.getMailServiceUrl()));
            ResponseEntity<SendMailResponse> mailResponse = restTemplate.postForEntity(appConfig.getMailServiceUrl(), requestEntity, SendMailResponse.class);
            return buildSendMailResponse(mailResponse);
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
                String responseBody = ex.getResponseBodyAsString();
                // Handle the response body indicating the invalid parameter
                throw new MailServiceException("Bad Request: " + responseBody);
            } else {
                // Handle other HttpClientErrorException cases
                throw ex;
            }
        }
//        HttpHeaders headers = addRequestHeaders();
//        RequestEntity<SendMailRequest> requestEntity = new RequestEntity<>(mailRequest, headers, POST, URI.create(appConfig.getMailServiceUrl()));
//        ResponseEntity<SendMailResponse> mailResponse =restTemplate.postForEntity(appConfig.getMailServiceUrl(), requestEntity,SendMailResponse.class);
//        return buildSendMailResponse(mailResponse);

    }

    private HttpHeaders addRequestHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        headers.setAccept(List.of(APPLICATION_JSON));
        headers.set("api-key", appConfig.getMailApiKey());
        return headers;
    }
    private static SendMailResponse buildSendMailResponse(ResponseEntity<SendMailResponse> mailResponse) {
        HttpStatusCode code = mailResponse.getStatusCode();
        var response = mailResponse.getBody();
        if (response==null) throw new RuntimeException("Mail Sending failed");
        response.setStatusCode(code.value());
        return response;
    }
}
