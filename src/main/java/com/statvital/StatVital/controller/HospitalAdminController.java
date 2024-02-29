package com.statvital.StatVital.controller;


import com.statvital.StatVital.dtos.request.SignUpHospitalAdminRequest;
import com.statvital.StatVital.dtos.response.SignInHospitalAdminResponse;
import com.statvital.StatVital.services.HospitalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class HospitalAdminController {

    private HospitalService hospitalService;
    @PostMapping("/signInHospital")
    public SignInHospitalAdminResponse signIn (@RequestBody SignUpHospitalAdminRequest request){

       try{
            SignInHospitalAdminResponse signInHospitalAdminResponse =hospitalService.signup(request);
            return signInHospitalAdminResponse;
       }catch (Exception error){
           SignInHospitalAdminResponse errorResponse = new SignInHospitalAdminResponse();

            return errorResponse;
       }
    }
}
