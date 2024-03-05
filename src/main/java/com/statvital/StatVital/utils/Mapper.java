package com.statvital.StatVital.utils;

import com.statvital.StatVital.data.model.Child;
import com.statvital.StatVital.data.model.HospitalAdmin;
import com.statvital.StatVital.dtos.request.ChildRequest;
import com.statvital.StatVital.dtos.request.SignUpHospitalAdminRequest;
import com.statvital.StatVital.dtos.response.SignInHospitalAdminResponse;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Mapper {
    public static HospitalAdmin hospitalMapper(SignUpHospitalAdminRequest request){
        HospitalAdmin hospitalAdmin = new HospitalAdmin();
        hospitalAdmin.setFacilityName(request.getFacilityName());
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
        child.setDob(childRequest.getDob());
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
}
