package com.statvital.StatVital.utils;

import com.statvital.StatVital.data.model.HospitalAdmin;
import com.statvital.StatVital.data.model.MorgueAdmin;
import com.statvital.StatVital.dtos.request.SignUpMorgueRequest;
import com.statvital.StatVital.dtos.response.SignInHospitalAdminResponse;
import com.statvital.StatVital.dtos.response.SignUpMorgueResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MorgueMapper {
    public static MorgueAdmin morgueMap(SignUpMorgueRequest request){
        MorgueAdmin morgueAdmin = new MorgueAdmin();
        morgueAdmin.setFacilityName(request.getFacilityName());
        morgueAdmin.setPassword(request.getPassword());
        return morgueAdmin;
    }

    public static SignUpMorgueResponse mapper(MorgueAdmin morgueAdmin){
        SignUpMorgueResponse response = new SignUpMorgueResponse();
        response.setEmail(morgueAdmin.getEmail());
        response.setRegisterDate(DateTimeFormatter
                .ofPattern("EEE dd/MMM/yyyy HH:mm:ss a")
                .format(LocalDateTime.now()));
        response.setMessage("Successfully Registered");
        response.setFacilityName(morgueAdmin.getFacilityName());


        return response;
    }
}
