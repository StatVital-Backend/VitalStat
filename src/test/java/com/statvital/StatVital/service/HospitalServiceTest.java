package com.statvital.StatVital.service;


import com.statvital.StatVital.data.model.HospitalAdmin;
import com.statvital.StatVital.data.repository.HospitalAdminRepo;
import com.statvital.StatVital.dtos.request.SignInHospitalRequest;
import com.statvital.StatVital.dtos.request.SignUpHospitalAdminRequest;
import com.statvital.StatVital.dtos.response.LogInAdminResponse;
import com.statvital.StatVital.exceptions.HospitalAlreadyExist;
import com.statvital.StatVital.services.HospitalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
//@AllArgsConstructor
public class HospitalServiceTest {

    @Autowired
    private  HospitalAdminRepo hospitalAdminRepo;
    @Autowired
    private  HospitalService hospitalService;
    @BeforeEach
    public void doThis(){
        hospitalAdminRepo.deleteAll();
    }

    @Test
    public void testThat_An_HospitalAdminCanRegister(){
        SignUpHospitalAdminRequest request = new SignUpHospitalAdminRequest();
        request.setFacilityName("Kings Court Hospital");
        request.setSector("Public");
        request.setEmail("kch@gmail.com");
        request.setPassword("password");

        hospitalService.signup(request);
        assertThat(hospitalAdminRepo.count(), is(1L));


    }
    @Test
    public void testToRegisterHospitalWithUniqueName(){
        SignUpHospitalAdminRequest request = new SignUpHospitalAdminRequest();
        request.setFacilityName("Kings Court Hospital");
        request.setSector("Public");
        request.setEmail("kch@gmail.com");
        request.setPassword("password");

        hospitalService.signup(request);
        assertThat(hospitalAdminRepo.count(), is(1L));

        SignUpHospitalAdminRequest request1 = new SignUpHospitalAdminRequest();
        request1.setFacilityName("Kings Court Hospital");
        request1.setSector("Public");
        request1.setEmail("kch@gmail.com");
        request1.setPassword("password");



//        hospitalService.signup(request1);
//        List<HospitalAdmin> all = hospitalAdminRepo.findAll();
//        System.out.println(all);
//        assertThat(all.size(), is(1));

        assertThrows(HospitalAlreadyExist.class, ()->hospitalService.signup(request1));

    }


    @Test
    public void testThatMultipleHospitalsCanBeRegistered(){
        SignUpHospitalAdminRequest request = new SignUpHospitalAdminRequest();
        request.setFacilityName("Kings Court Hospital");
        request.setSector("Public");
        request.setEmail("kch@gmail.com");
        request.setPassword("password");

        hospitalService.signup(request);
        assertThat(hospitalAdminRepo.count(), is(1L));

        SignUpHospitalAdminRequest request1 = new SignUpHospitalAdminRequest();
        request1.setFacilityName("Mimmcie Hospital");
        request1.setSector("Public");
        request1.setEmail("mih@gmail.com");
        request1.setPassword("password");

        hospitalService.signup(request1);
        assertThat(hospitalAdminRepo.count(), is(2L));
    }

    @Test
    public void testThatRegisteredHospitalCanLogin(){
        SignUpHospitalAdminRequest request = new SignUpHospitalAdminRequest();
        request.setFacilityName("Pascal Hospital");
        request.setEmail("ph@gmail.com");
        request.setSector("Private");
        request.setPassword("password");

        hospitalService.signup(request);
        assertThat(hospitalAdminRepo.count(), is(1L));

        SignInHospitalRequest sign = new SignInHospitalRequest();
        sign.setFacilityName("Pascal Hospital");
        sign.setEmail("ph@gmail.com");
        sign.setPassword("password");
        System.out.println(sign);
        LogInAdminResponse logInAdminResponse = hospitalService.logIn(sign);
        assertThat(logInAdminResponse.isLoggedIn(), is(true));


    }

}
