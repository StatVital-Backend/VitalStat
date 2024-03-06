//package com.statvital.StatVital.services;
//
//import com.statvital.StatVital.dtos.request.Recipient;
//import com.statvital.StatVital.dtos.request.SendMailRequest;
//import com.statvital.StatVital.dtos.request.Sender;
//import com.statvital.StatVital.dtos.response.SendMailResponse;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//@SpringBootTest
//@Slf4j
//@AllArgsConstructor
//class MailServiceImplTest {
//    private final MailService mailService;
//
//    @Test
//    public void testSendMail(){
//        SendMailRequest mailRequest = buildMailRequest();
//        SendMailResponse response = mailService.sendMail(mailRequest);
//        assertNotNull(response);
//        assertEquals(201, response.getStatusCode());
//    }
//
//    private static SendMailRequest buildMailRequest() {
//        //1. Create mail sending request
//        SendMailRequest mailRequest = new SendMailRequest();
//        //2. Create Sender
//        Sender sender = new Sender("acebook", "acebook@semicolon.africa");
//        //3. Create Recipient Collection
//        List<Recipient> recipients = List.of(
//                new Recipient("moyin", "udeheddie01@hotmail.com")
//        );
//        mailRequest.setSubject("testing 1,2,3...");
//        mailRequest.setHtmlContent("<p>Hello Semicolon</p>");
//        mailRequest.setSender(sender);
//        mailRequest.setRecipients(recipients);
//        return mailRequest;
//    }
//
//}