package com.statvital.StatVital.utils;

import com.statvital.StatVital.data.model.HospitalAdmin;
import com.statvital.StatVital.dtos.request.SignUpHospitalAdminRequest;
import com.statvital.StatVital.dtos.response.SignInHospitalAdminResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Mapper {
    public static HospitalAdmin hospitalMapper(SignUpHospitalAdminRequest request){
        HospitalAdmin hospitalAdmin = new HospitalAdmin();
        hospitalAdmin.setEmail(request.getFacilityName());
        hospitalAdmin.setPassword(request.getPassword());
        return hospitalAdmin;
    }

    public static SignInHospitalAdminResponse responseMapper(HospitalAdmin hospitalAdmin){
        SignInHospitalAdminResponse response = new SignInHospitalAdminResponse();
        response.setUsername(hospitalAdmin.getEmail());
        response.setRegisterDate(DateTimeFormatter
                .ofPattern("EEE dd/MMM/yyyy HH:mm:ss a")
                .format(LocalDateTime.now()));
        response.setMessage("Successfully Registered");


        return response;
    }
}
