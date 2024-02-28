package com.statvital.StatVital.service;


import com.statvital.StatVital.data.repository.HospitalAdminRepo;
import com.statvital.StatVital.dtos.request.SignUpHospitalAdminRequest;
import com.statvital.StatVital.services.HospitalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

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

        hospitalService.signin(request);
        assertThat(hospitalAdminRepo.count(), is(1L));


    }

}
