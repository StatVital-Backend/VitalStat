package com.statvital.StatVital.controller;


import com.statvital.StatVital.dtos.request.DeathReq;
import com.statvital.StatVital.dtos.request.SearchDeathRequest;
import com.statvital.StatVital.dtos.request.SignInMorgueRequest;
import com.statvital.StatVital.dtos.request.SignUpMorgueRequest;
import com.statvital.StatVital.dtos.response.ApiResponse;
import com.statvital.StatVital.dtos.response.LogInMorgueResponse;
import com.statvital.StatVital.dtos.response.RegisterDeathResponse;
import com.statvital.StatVital.dtos.response.SignUpMorgueResponse;
import com.statvital.StatVital.services.MorgueService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "#")
@RequestMapping("/api/v2")
@AllArgsConstructor
public class MorgueController {
    private MorgueService morgueService;


    @PostMapping("/Sign-In-Morgue")
    public SignUpMorgueResponse signIn(@RequestBody SignUpMorgueRequest signUpMorgueRequest){
        try {
            return morgueService.signup(signUpMorgueRequest);
        }catch (Exception e){
            return new SignUpMorgueResponse();
        }
    }


    @PostMapping("/Log-In-Morgue")
    public LogInMorgueResponse logIn(@RequestBody SignInMorgueRequest signInMorgueRequest){
        try {
            return morgueService.loginMorgue(signInMorgueRequest);
        }catch (Exception e){
            return new LogInMorgueResponse();
        }
    }
    @PostMapping("/RegisterDeath")
    public RegisterDeathResponse registerDeath(@RequestBody DeathReq deathReq){
        try{
            return morgueService.registerBody(deathReq);

        }catch (Exception e){
            return new RegisterDeathResponse();
        }

    }

    @PostMapping("searchDeath")
    public ApiResponse<?> searchDeath(@RequestBody SearchDeathRequest searchDeathRequest){
        try {
           return ApiResponse.success(morgueService.searchDeath(searchDeathRequest.getDeceasedName()),
                   "Morgue Found");
        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
    }

    @GetMapping("getData")
    public ApiResponse<?> getDeathData(){

        try {
            return ApiResponse.success(morgueService.getData(),"Successful");
        }catch (Exception error){
            return ApiResponse.error(error.getMessage());
        }
    }



}
