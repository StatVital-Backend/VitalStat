package com.statvital.StatVital.controller;


import com.statvital.StatVital.data.model.Child;
import com.statvital.StatVital.dtos.request.*;
import com.statvital.StatVital.dtos.response.LogInAdminResponse;
import com.statvital.StatVital.dtos.response.RegisterChildResponse;
import com.statvital.StatVital.dtos.response.SignInHospitalAdminResponse;
import com.statvital.StatVital.services.HospitalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "#")
@RequestMapping("/api/v1")
@AllArgsConstructor
public class HospitalAdminController {

    private HospitalService hospitalService;
    @PostMapping("/signInHospital")
    public SignInHospitalAdminResponse signIn (@RequestBody SignUpHospitalAdminRequest request){

       try{
            SignInHospitalAdminResponse signInHospitalAdminResponse = hospitalService.signup(request);
            return signInHospitalAdminResponse;
       }catch (Exception error){
           SignInHospitalAdminResponse errorResponse = new SignInHospitalAdminResponse();

            return errorResponse;
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

    @GetMapping("/Search{name}")
    public Object searchChildRes (@PathVariable SearchChildReq name){
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


}
