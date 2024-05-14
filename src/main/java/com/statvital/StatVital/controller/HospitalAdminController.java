package com.statvital.StatVital.controller;


import com.statvital.StatVital.dtos.request.*;
import com.statvital.StatVital.dtos.response.*;
import com.statvital.StatVital.services.DataBaseService;
import com.statvital.StatVital.services.HospitalService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
@AllArgsConstructor
@Slf4j
public class HospitalAdminController {
    private final HospitalService hospitalService;


    @PostMapping("/signUpHospital")
    public ApiResponse<?> signIn (@RequestBody SignUpHospitalAdminRequest request){
       try{
           log.info("i am in the controller");
          return ApiResponse.success(hospitalService.signup(request), "Sign Up Successfully");
       }catch (Exception error){
         return ApiResponse.error("Sign Up Failed ");
       }
    }

    @PostMapping("/logInHospital")
    public ApiResponse<?> login (@RequestBody SignInHospitalRequest request ){

        try{
            log.info("I am in the LogIn Controller");
            return ApiResponse.success(hospitalService.logIn(request), "LogIn Successful");
        }catch (Exception error){
            return ApiResponse.error("Login Failed");
        }
    }

    @PostMapping("/RegisterChild")
    public RegisterChildResponse registerChild (@RequestBody ChildRequest childRequest){
        try{
            return hospitalService.registerChild(childRequest);

        }catch (Exception error){
            return new RegisterChildResponse();
        }
    }
    @PostMapping("/registerDeath")
    public RegisterDeathResponse registerDeath(@RequestBody DeathReq deathReq){

        try{
            return hospitalService.registerBody(deathReq);

        }catch (Exception error){
            return new RegisterDeathResponse();
        }
    }


    @GetMapping("/searchChild")
    public Object searchChildRes(@RequestBody SearchChildReq name){
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

    @PutMapping("updateChildInfo")
    public ApiResponse<?> updateInfo(@RequestBody UpdateChildReq updateChildReq ){
        try {
            return ApiResponse.success(hospitalService.updateChildInfo(updateChildReq),
                    "Updated Successfully");
        }catch (Exception e){
            return ApiResponse.error("An error occurred, Child not updated");
        }
    }

    @GetMapping("/searchBody")
    public ApiResponse<?> searchDeceasedRes(@RequestBody SearchDeathRequest searchDeathRequest){
        try{
            return ApiResponse.success(hospitalService.hosSearchDeceased(searchDeathRequest),
                    "Deceased Found in Hospital");
        }catch (Exception e){
            return ApiResponse.error("Deceased Not Found");
        }

    }


}
