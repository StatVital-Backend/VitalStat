package com.statvital.StatVital.services;

//import com.statvital.StatVital.data.model.Confirmation;

import com.statvital.StatVital.config.AppConfig;
import com.statvital.StatVital.data.model.Child;
import com.statvital.StatVital.data.model.Death;
import com.statvital.StatVital.data.model.HospitalAdmin;
import com.statvital.StatVital.data.model.Role;
import com.statvital.StatVital.data.repository.ChildRepository;
import com.statvital.StatVital.data.repository.DeathRepo;
import com.statvital.StatVital.data.repository.HospitalAdminRepo;
import com.statvital.StatVital.data.repository.HospitalDeathRepo;
import com.statvital.StatVital.dtos.request.*;
import com.statvital.StatVital.dtos.response.LogInAdminResponse;
import com.statvital.StatVital.dtos.response.RegisterChildResponse;
import com.statvital.StatVital.dtos.response.RegisterDeathResponse;
import com.statvital.StatVital.dtos.response.SignInHospitalAdminResponse;
import com.statvital.StatVital.exceptions.ChildExist;
import com.statvital.StatVital.exceptions.ChildNotFound;
import com.statvital.StatVital.exceptions.HospitalAlreadyExist;
import com.statvital.StatVital.exceptions.HospitalNotFound;
import com.statvital.StatVital.security.services.JwtServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.statvital.StatVital.utils.Mapper.*;

@Service
@AllArgsConstructor
@Slf4j
public class HospitalServiceImpl implements HospitalService {
    private final HospitalAdminRepo hospitalAdminRepo;
    private final ChildRepository childRepository;
    private final ChildService childService;
    private final DeathService deathService;
    private final MailService mailService;
    private final JwtServiceImpl jwtService;
    private final HospitalDeathRepo hospitalDeathRepo;
    private final AuthenticationProvider authenticationProvider;
    private final PasswordEncoder passwordEncoder;



    @Override
    public SignInHospitalAdminResponse signup(SignUpHospitalAdminRequest request) {
      Optional<HospitalAdmin> existedHospital = hospitalAdminRepo.
              findHospitalAdminByEmailIgnoreCase(
                      request.getEmail());
        if(existedHospital.isPresent()){
            throw new RuntimeException("Hospital Already Exist");
        }

        HospitalAdmin registeredHospital = HospitalAdmin.builder()
                .facilityName(request.getFacilityName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.HOSPITAL_ADMIN)
                .build();

        hospitalAdminRepo.save(registeredHospital);


        String jwtToken = jwtService.generateToken( registeredHospital);
        return SignInHospitalAdminResponse.builder()
                .facilityName(registeredHospital.getFacilityName())
                .email(registeredHospital.getEmail())
                .token(jwtToken)
                .registerDate(LocalDate.from(LocalDateTime.now()))
                .message("Hospital successfully registered")
                .build();
    }

    @Override
    public LogInAdminResponse logIn(SignInHospitalRequest request) {
        log.info("I am in the login service");
        try {
            log.info("I am in the auth provider");
            authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getFacilityName(), request.getPassword())
            );

            log.info("looking for hospital");
            HospitalAdmin user = hospitalAdminRepo.findHospitalAdminByFacilityName(request.getFacilityName()).orElse(null);
            if (user == null) {
                return null;
            }
            log.info("found hospital");

            var jwtToken = jwtService.generateToken( user);
            log.info("generated token");
            return LogInAdminResponse.builder()
                    .token(jwtToken)
                    .message("successfully logged in")
                    .build();
        } catch (AuthenticationException e) {
            return LogInAdminResponse.builder().message("Authentication failed for user {}" + e.getMessage()).build();
        }


    }





    @Override
    public RegisterChildResponse registerChild(ChildRequest childRequest) {

        findRegisteredChild(generateReferenceId());

        childService.registerChild(childRequest);
        RegisterChildResponse response = new RegisterChildResponse();
        response.setMessage("Child Registered Successfully");
        response.setDateRegistered(LocalDateTime.now());
        response.setId(generateReferenceId());

        return response;
    }


    @Override
    public RegisterDeathResponse registerBody(DeathReq deathReq) {
        deathService.registerDeath(deathReq);
        RegisterDeathResponse response = new RegisterDeathResponse();
        response.setMessage("Registered Successfully ");
        response.setDateRegistered(LocalDateTime.now());
        response.setId(generateReferenceId());
        return response;
    }


    private void findRegisteredChild(String referenceId) {
        Optional<Child>child = childRepository.findChildByReferenceId(referenceId);
        if(child.isPresent()) throw new ChildExist("Child Already Exist");
    }

    @Override
    public String deleteChildInfo(DeleteChildReq deleteChildReq) {
        childService.deleteProfile(deleteChildReq);
        return null;
    }

    @Override
    public Child searchChild(SearchChildReq searchChildReq) {
        Optional<Child> child = childService.findChild(searchChildReq.getName());
        if (child.isEmpty()) throw new ChildNotFound("Child not found");
        return child.get();
    }

    @Override
    public Child updateChildInfo(UpdateChildReq updateChildReq) {
        Child child = findChild(updateChildReq.getName());
        if(child == null){
            throw new ChildNotFound("Child Not Found");
        }

        if (updateChildReq.getName() != null) {
            child.setName(updateChildReq.getName());
        }
        if (updateChildReq.getAge() != null) {
            child.setAge(updateChildReq.getAge());
        }
        childRepository.save(child);

        return child;
    }

//    @Override
//    public List<Child> searchChild(SearchChildReq searchChildReq) {
//        return childRepository.findChildByNameIsContaining(searchChildReq.getName());
//    }

    @Override
    public List<Death> hosSearchDeceased(SearchDeathRequest searchDeathRequest) {
        return hospitalDeathRepo.findDeathByDeceasedName(searchDeathRequest.getDeceasedName());
    }

    @Override
    public List<Death> getAllDeceasedInfo() {
        return hospitalDeathRepo.findAll();
    }

    @Override
    public List<HospitalAdmin> getChildren() {
        return hospitalAdminRepo.findAll();
    }


    private Child findChild(String id) {
        Optional<Child> childOptional = childRepository.findChildByReferenceId(id);
        if (childOptional.isEmpty()) {
            throw new ChildNotFound("Child not found");
        }
        return childOptional.get();
    }



    private Optional<HospitalAdmin> getAdmin(String email) {
        Optional<HospitalAdmin> admin =hospitalAdminRepo.findHospitalAdminByEmailIgnoreCase(email);
        if(admin.isEmpty())
            throw new HospitalNotFound("Hospital Does not exist");
//            return admin.get();
        return admin;
    }

//    private Optional<HospitalAdmin> retrieveAdmin(String email) {
//        Optional<HospitalAdmin> newAdmin = retrieveAdmin(email);
//        return null;
//    }

    private void findHospital(String email) {
        Optional<HospitalAdmin> admin = hospitalAdminRepo.findHospitalAdminByEmailIgnoreCase(email);
        if(admin.isPresent())
//            System.out.println("Hospital Already exist");
            throw new HospitalAlreadyExist("Hospital Already Exist");
    }
}
