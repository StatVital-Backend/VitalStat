package com.statvital.StatVital.services;

import com.statvital.StatVital.data.model.Child;
import com.statvital.StatVital.data.model.Death;
import com.statvital.StatVital.data.model.HospitalAdmin;
import com.statvital.StatVital.data.model.MorgueAdmin;
import com.statvital.StatVital.data.repository.MorgueAdminRepo;
import com.statvital.StatVital.dtos.request.DeathReq;
import com.statvital.StatVital.dtos.request.SignInMorgueRequest;
import com.statvital.StatVital.dtos.request.SignUpMorgueRequest;
import com.statvital.StatVital.dtos.response.LogInAdminResponse;
import com.statvital.StatVital.dtos.response.LogInMorgueResponse;
import com.statvital.StatVital.dtos.response.RegisterDeathResponse;
import com.statvital.StatVital.dtos.response.SignUpMorgueResponse;
import com.statvital.StatVital.exceptions.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.statvital.StatVital.utils.Mapper.generateReferenceId;
import static com.statvital.StatVital.utils.MorgueMapper.mapper;
import static com.statvital.StatVital.utils.MorgueMapper.morgueMap;

@Service
@AllArgsConstructor
public class MorgueServiceImpl implements MorgueService{

    private final MorgueAdminRepo morgueAdminRepo;
    private final DeathService deathService;

    @Override
    public SignUpMorgueResponse signup(SignUpMorgueRequest request) {
        findMorgue(request.getFacilityName());
        return mapper(morgueAdminRepo.save(morgueMap(request)));
    }

    @Override
    public LogInMorgueResponse loginMorgue(SignInMorgueRequest request) {
       MorgueAdmin morgueAdmin = getMorgue(request.getFacilityName());
       validatePassword(request, morgueAdmin);

       morgueAdmin.setLoggedIn(true);
       MorgueAdmin morgueAdmin1 = morgueAdminRepo.save(morgueAdmin);

       LogInMorgueResponse logInMorgueResponse = new LogInMorgueResponse();
       logInMorgueResponse.setMessage("Successfully Logged on");
       logInMorgueResponse.setLogInDate(String.valueOf(LocalDateTime.now()));
       logInMorgueResponse.setLoggedIn(morgueAdmin1.isLoggedIn());


        return logInMorgueResponse;

    }

    private void validatePassword(SignInMorgueRequest request, MorgueAdmin morgueAdmin) {
        if(!morgueAdmin.getPassword().equals(request.getPassword()))
            throw new IncorrectCredentials("Incorrect Username or Password");
    }

    private MorgueAdmin getMorgue(String facilityName) {
        Optional<MorgueAdmin> morgueAdmin = morgueAdminRepo.findMorgueAdminByFacilityName(facilityName);
        if(morgueAdmin.isEmpty()) throw new HospitalNotFound("Morgue not found");
        return morgueAdmin.get();
    }



    private void findMorgue(String facilityName) {
        Optional<MorgueAdmin> admin = morgueAdminRepo.findMorgueAdminByFacilityName(facilityName);
        if(admin.isPresent())
            throw new MorgueAlreadyExist("Morgue Already Exist");
    }

    @Override
    public RegisterDeathResponse registerBody(DeathReq deathReq) {
        deathService.registerDeath(deathReq);
        RegisterDeathResponse response = new RegisterDeathResponse();
        response.    setMessage("Successfully Registered ");
        response.setDateRegistered(LocalDateTime.now());
        response.setId(generateReferenceId());
        return response;
    }

    @Override
    public Death searchDeath(String name) {
        Optional<Death> death = deathService.findBody(name);
        if (death.isEmpty()) throw new ChildNotFound("Corpse not found");
        return death.get();
    }


}
