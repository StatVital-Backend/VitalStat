package com.statvital.StatVital.services;

import com.statvital.StatVital.data.repository.MorgueAdminRepo;
import com.statvital.StatVital.dtos.request.SignInMorgueRequest;
import com.statvital.StatVital.dtos.request.SignUpMorgueRequest;
import com.statvital.StatVital.dtos.response.LogInMorgueResponse;
import com.statvital.StatVital.exceptions.MorgueAlreadyExist;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MorgueServiceImplTest {

    @Autowired
    private MorgueService morgueService;
    @Autowired
    private MorgueAdminRepo morgueAdminRepo;

    @BeforeEach
    public void doThis(){
        morgueAdminRepo.deleteAll();
    }


    @Test
    public void testThatMorgueAdminCanSignUp(){
        SignUpMorgueRequest signUpMorgueRequest = new SignUpMorgueRequest();
        signUpMorgueRequest.setFacilityName("KuntaKinte");
        signUpMorgueRequest.setEmail("kk@gmail.com");
        signUpMorgueRequest.setPassword("password");

        morgueService.signup(signUpMorgueRequest);
        assertThat(morgueAdminRepo.count(), is(1L));
    }

    @Test
    public void testThatOnlyUniqueMorgueAdminCanRegister(){
        SignUpMorgueRequest signUpMorgueRequest = new SignUpMorgueRequest();
        signUpMorgueRequest.setFacilityName("KuntaKinte");
        signUpMorgueRequest.setEmail("kk@gmail.com");
        signUpMorgueRequest.setPassword("password");

        morgueService.signup(signUpMorgueRequest);
        assertThat(morgueAdminRepo.count(), is(1L));

        SignUpMorgueRequest signUpMorgueRequest1 = new SignUpMorgueRequest();
        signUpMorgueRequest1.setFacilityName("KuntaKinte");
        signUpMorgueRequest1.setEmail("kk@gmail.com");
        signUpMorgueRequest1.setPassword("password");

        assertThrows(MorgueAlreadyExist.class,()->morgueService.signup(signUpMorgueRequest1));
    }

    @Test
    public void testThatRegisteredUserCanLogIn(){
        SignUpMorgueRequest signUpMorgueRequest = new SignUpMorgueRequest();
        signUpMorgueRequest.setFacilityName("KuntaKinte");
        signUpMorgueRequest.setEmail("kk@gmail.com");
        signUpMorgueRequest.setPassword("password");

        morgueService.signup(signUpMorgueRequest);
        assertThat(morgueAdminRepo.count(), is(1L));

        SignInMorgueRequest signInMorgueRequest = new SignInMorgueRequest();
        signInMorgueRequest.setFacilityName("KuntaKinte");
        signInMorgueRequest.setEmail("kk@gmail.com");
        signInMorgueRequest.setPassword("password");

        LogInMorgueResponse logInMorgueResponse = morgueService.loginMorgue(signInMorgueRequest);
        assertThat(logInMorgueResponse.isLoggedIn(), is(true));


    }

//    @Test
//    public void testThatMultipleMorguesCanRegister(){
//        SignUpMorgueRequest signUpMorgueRequest = new SignUpMorgueRequest();
//        signUpMorgueRequest.setFacilityName("KuntaKinte");
//        signUpMorgueRequest.setEmail("kk@gmail.com");
//        signUpMorgueRequest.setPassword("password");
//
//        morgueService.signup(signUpMorgueRequest);
//        assertThat(morgueAdminRepo.count(), is(1L));
//
//        SignUpMorgueRequest signUpMorgueRequest1 = new SignUpMorgueRequest();
//        signUpMorgueRequest1.setFacilityName("Kogberegbe");
//        signUpMorgueRequest1.setEmail("kg@gmail.com");
//        signUpMorgueRequest1.setPassword("password");
//
//        morgueService.signup(signUpMorgueRequest);
//        assertThat(morgueAdminRepo.count(), is(2L));
//
//    }

}