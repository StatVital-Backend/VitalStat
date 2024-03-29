package com.statvital.StatVital.service;


import com.statvital.StatVital.config.AppConfig;
import com.statvital.StatVital.data.model.HospitalAdmin;
import com.statvital.StatVital.data.repository.ChildRepository;
import com.statvital.StatVital.data.repository.HospitalAdminRepo;
import com.statvital.StatVital.dtos.request.*;
import com.statvital.StatVital.dtos.response.LogInAdminResponse;
import com.statvital.StatVital.dtos.response.SendMailResponse;
import com.statvital.StatVital.exceptions.HospitalAlreadyExist;
import com.statvital.StatVital.exceptions.HospitalNotFound;
import com.statvital.StatVital.exceptions.IncorrectCredentials;
import com.statvital.StatVital.services.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
//import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
//@AllArgsConstructor
public class HospitalServiceTest {


    @Autowired
    private  HospitalAdminRepo hospitalAdminRepo;
    @Autowired
    private  HospitalService hospitalService;
    @Autowired
    private ChildService childService;
    @Autowired
    private ChildRepository childRepository;
    @Autowired
    private MailService mailService;
    @BeforeEach
    public void doThis(){
        hospitalAdminRepo.deleteAll();
        childRepository.deleteAll();
    }

    @Test
    public void testThat_An_HospitalAdminCanRegister(){
        SignUpHospitalAdminRequest request = new SignUpHospitalAdminRequest();
        request.setFacilityName("Lance Hospital");
        request.setSector("Private");
        request.setEmail("ejioforkelvin@gmail.com");
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
        request.setFacilityLocation("Ikorodu");


        hospitalService.signup(request);
        assertThat(hospitalAdminRepo.count(), is(1L));

        SignInHospitalRequest sign = new SignInHospitalRequest();
        sign.setFacilityName("Pascal Hospital");
        sign.setEmail("ph@gmail.com");
        sign.setPassword("password");

        LogInAdminResponse logInAdminResponse = hospitalService.logIn(sign);
        System.out.println(logInAdminResponse.getToken());
        assertThat(logInAdminResponse.isLoggedIn(), is(true));



    }

    @Test
    public void testThatAnUnregisteredHospitalCannotLogIn(){
        SignInHospitalRequest sign = new SignInHospitalRequest();
        sign.setFacilityName("Pascal Hospital");
        sign.setEmail("ph@gmail.com");
        sign.setPassword("password");


        assertThrows(HospitalNotFound.class, ()->hospitalService.logIn(sign));

    }
//    @Test
//    public void testThatRegisteredHospitalCanLogInWithCorrectPassword(){
//        SignUpHospitalAdminRequest request = new SignUpHospitalAdminRequest();
//        request.setFacilityName("Pascal Hospital");
//        request.setEmail("ph@gmail.com");
//        request.setSector("Private");
//        request.setPassword("password");
//
//        hospitalService.signup(request);
//        assertThat(hospitalAdminRepo.count(), is(1L));
//
//        SignInHospitalRequest sign = new SignInHospitalRequest();
//        sign.setFacilityName("Pascal Hospital");
//        sign.setEmail("ph@gmail.com");
//        sign.setPassword("pasword");
//         assertThrows(IncorrectCredentials.class, ()-> hospitalService.logIn(sign));
//
//    }
    @Test
    public void testThatChildCanBeRegistered(){
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


        ChildRequest childRequest = new ChildRequest();
        childRequest.setChildName("Seyi");
        childRequest.setMotherName("Esther");
        childRequest.setFatherName("Julius");

        hospitalService.registerChild(childRequest);
        assertThat(hospitalAdminRepo.count(), is(1L));
    }

    @Test
    public void testToRegisterMultipleChildren(){
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


        ChildRequest childRequest = new ChildRequest();
        childRequest.setChildName("Seyi");
        childRequest.setMotherName("Esther");
        childRequest.setFatherName("Julius");

        hospitalService.registerChild(childRequest);
        assertThat(childRepository.count(), is(1L));

        ChildRequest childRequest1 = new ChildRequest();
        childRequest1.setChildName("ANu");
        childRequest1.setMotherName("Saka");
        childRequest1.setFatherName("Tinubu");

        hospitalService.registerChild(childRequest1);
        assertThat(childRepository.count(), is(2L));

    }
    @Test
    public void testThatRegisteredChildCanBeDeleted(){
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
        LogInAdminResponse logInAdminResponse = hospitalService.logIn(sign);
        assertThat(logInAdminResponse.isLoggedIn(), is(true));


        ChildRequest childRequest = new ChildRequest();
        childRequest.setName("Mint");
        childRequest.setMotherName("Mute");
        childRequest.setFatherName("Julius");
        childRequest.setSex("Female");

        hospitalService.registerChild(childRequest);
        assertThat(childRepository.count(), is(1L));

        ChildRequest childRequest1 = new ChildRequest();
        childRequest1.setName("Kuli");
        childRequest1.setMotherName("Saka");
        childRequest1.setFatherName("Tinubu");
        childRequest1.setSex("Female");

        hospitalService.registerChild(childRequest1);
        assertThat(childRepository.count(), is(2L));

        DeleteChildReq deleteChildReq = new DeleteChildReq();
        deleteChildReq.setChildName("Mint");

        hospitalService.deleteChildInfo(deleteChildReq);
        assertThat(childRepository.count(), is(1L));

    }

    @Test
    public void testThatRegisteredChildCanBeUpdated(){
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
        LogInAdminResponse logInAdminResponse = hospitalService.logIn(sign);
        assertThat(logInAdminResponse.isLoggedIn(), is(true));


        ChildRequest childRequest = new ChildRequest();
        childRequest.setName("Moses");
        childRequest.setSex("Female");
        childRequest.setMotherName("Mary");
        childRequest.setFatherName("Joseph");


        hospitalService.registerChild(childRequest);
        assertThat(childRepository.count(), is(1L));

    }
    @Test
    public void testSendMail(){
        SendMailRequest mailRequest = buildMailRequest();
        SendMailResponse response = mailService.sendMail(mailRequest);
        assertNotNull(response);
        assertEquals(201, response.getStatusCode());
    }

    private static SendMailRequest buildMailRequest() {
        //1. Create mail sending request
        SendMailRequest mailRequest = new SendMailRequest();
        //2. Create Sender
        Sender sender = new Sender("StatVital", "statvital99@gmail.com");
        //3. Create Recipient Collection
        List<Recipient> recipients = List.of(
                new Recipient("Anu", "anuoluwapobanwo@gmail.com")
        );
        mailRequest.setSubject("Stat-Vital");
        mailRequest.setHtmlContent("<p>Hello Seyi</p>");
        mailRequest.setSender(sender);
        mailRequest.setRecipients(recipients);
        return mailRequest;
    }




}
