package com.statvital.StatVital.controller;


import com.statvital.StatVital.dtos.request.SignInMorgueRequest;
import com.statvital.StatVital.dtos.request.SignUpMorgueRequest;
import com.statvital.StatVital.dtos.response.LogInMorgueResponse;
import com.statvital.StatVital.dtos.response.SignUpMorgueResponse;
import com.statvital.StatVital.services.MorgueService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2")
@AllArgsConstructor
public class MorgueController {
    private MorgueService morgueService;


    @PostMapping("/Sign-In-Morgue")
    public SignUpMorgueResponse signIn(@RequestBody SignUpMorgueRequest signUpMorgueRequest){
        try {
            SignUpMorgueResponse signUpMorgueResponse = morgueService.signup(signUpMorgueRequest);
            return signUpMorgueResponse;
        }catch (Exception e){
            SignUpMorgueResponse response = new SignUpMorgueResponse();
            return response;
        }
    }


    @PostMapping("/Log-In-Morgue")
    public LogInMorgueResponse logIn(@RequestBody SignInMorgueRequest signInMorgueRequest){
        try {
            LogInMorgueResponse logIn = morgueService.loginMorgue(signInMorgueRequest);
            return logIn;
        }catch (Exception e){
            LogInMorgueResponse response = new LogInMorgueResponse();
            return response;
        }
    }

}
