package com.statvital.StatVital.utils;

import com.statvital.StatVital.data.model.Child;
import com.statvital.StatVital.data.model.HospitalAdmin;
import com.statvital.StatVital.dtos.request.*;
import com.statvital.StatVital.dtos.response.SignInHospitalAdminResponse;
import com.statvital.StatVital.services.MailServiceImpl;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public static HospitalAdmin hospitalMapper(SignUpHospitalAdminRequest request){
        HospitalAdmin hospitalAdmin = new HospitalAdmin();
        hospitalAdmin.setFacilityName(request.getFacilityName());
        hospitalAdmin.setFacilityLocation(request.getFacilityLocation());
        hospitalAdmin.setEmail(request.getEmail());
        hospitalAdmin.setPassword(request.getPassword());
        return hospitalAdmin;
    }

    public static SignInHospitalAdminResponse responseMapper(HospitalAdmin hospitalAdmin){
        SignInHospitalAdminResponse response = new SignInHospitalAdminResponse();
        response.setEmail(hospitalAdmin.getEmail());
        response.setRegisterDate(DateTimeFormatter
                .ofPattern("EEE dd/MMM/yyyy HH:mm:ss a")
                .format(LocalDateTime.now()));
        response.setMessage("Successfully Registered");
        response.setSuccessful(true);
        response.setFacilityName(hospitalAdmin.getFacilityName());


        return response;
    }

    public static Child mapChild(ChildRequest childRequest){
        Child child = new Child();
//        System.out.println(childRequest);
        child.setName(childRequest.getName());
        child.setDob(LocalDateTime.now());
        child.setFatherName(childRequest.getFatherName());
        child.setMotherName(childRequest.getMotherName());
        child.setSex(childRequest.getSex());
        child.setReferenceId(generateReferenceId());
        return child;
    }

    public static String generateReferenceId() {
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        StringBuilder stringBuilder = new StringBuilder();
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < 8; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }
        String id = stringBuilder.toString();
        System.out.println("Unique Identification Number:" + id);
        return id;
    }
    public static SendMailRequest mailMapper(SignUpHospitalAdminRequest request){
        SendMailRequest sendMailRequest1 = new SendMailRequest();
        Recipient recipient = new Recipient();
        Sender sender = new Sender();
        sender.setEmail("statvital99@gmail.com");
        sender.setName("StatVital");
        recipient.setEmail(request.getEmail());
        recipient.setName(request.getFacilityName());
        List<Recipient> recipients = new ArrayList<>();

         recipients.add(recipient);

        sendMailRequest1.setRecipients(recipients);
        sendMailRequest1.setSender(sender);
        sendMailRequest1.setSubject("Welcome to StatVital!!!");
        String name = request.getFacilityName();
        sendMailRequest1.setHtmlContent(String.format("<p>Hello, %s Kindly Click here to Login</p>", name));
        return sendMailRequest1;
    }
}
