package com.statvital.StatVital.controller;


import com.statvital.StatVital.dtos.request.*;
import com.statvital.StatVital.dtos.response.DataResponse;
import com.statvital.StatVital.dtos.response.LogInAdminResponse;
import com.statvital.StatVital.dtos.response.RegisterChildResponse;
import com.statvital.StatVital.dtos.response.SignInHospitalAdminResponse;
import com.statvital.StatVital.services.DataBaseService;
import com.statvital.StatVital.services.HospitalService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
@AllArgsConstructor
public class HospitalAdminController {
    private final HospitalService hospitalService;
    private final DataBaseService dataBaseService;
    @PostMapping("/signUpHospital")
    public ResponseEntity<SignInHospitalAdminResponse> signIn (@RequestBody SignUpHospitalAdminRequest request){
       try{
           SignInHospitalAdminResponse response = hospitalService.signup(request);
           return new ResponseEntity<>(response, HttpStatus.OK);
       }catch (Exception error){
           SignInHospitalAdminResponse signInHospitalAdminResponse  = new SignInHospitalAdminResponse();
           signInHospitalAdminResponse.setMessage(error.getMessage());
           return new ResponseEntity<>(signInHospitalAdminResponse, HttpStatus.NOT_IMPLEMENTED);
       }
    }

    @PostMapping("/logInHospital")
    public LogInAdminResponse login (@RequestBody SignInHospitalRequest request){
        try{
            LogInAdminResponse logInAdminResponse = hospitalService.logIn(request);
            return logInAdminResponse;
        }catch (Exception error){
            LogInAdminResponse errorResponse = new LogInAdminResponse();
            return errorResponse;
        }
    }

    @PostMapping("/RegisterChild")
    public RegisterChildResponse registerChild (@RequestBody ChildRequest childRequest){
        try{
            RegisterChildResponse response = hospitalService.registerChild(childRequest);
            return response;

        }catch (Exception error){
            RegisterChildResponse errorResponse = new RegisterChildResponse();
            return errorResponse;
        }
    }

    @PostMapping("/Search")
    public Object searchChildRes (@RequestBody SearchChildReq name){
        System.out.println("I m searching for..." + name);
        try{
           return hospitalService.searchChild(name);
        }catch (Exception e){
            return e.getMessage();
        }

    }

    @DeleteMapping("/DeleteChild")
    public String deleteChild(@RequestBody DeleteChildReq deleteChildReq){
        try {
            return hospitalService.deleteChildInfo(deleteChildReq);
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("/GetData")
    public String dataData(){
        try {
            return hospitalService.getChildren().toString();

        } catch (Exception e) {
            return e.getMessage();
        }
    }


}
