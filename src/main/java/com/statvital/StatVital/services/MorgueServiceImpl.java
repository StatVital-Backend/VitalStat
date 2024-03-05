package com.statvital.StatVital.services;

import com.statvital.StatVital.data.model.HospitalAdmin;
import com.statvital.StatVital.data.model.MorgueAdmin;
import com.statvital.StatVital.data.repository.MorgueAdminRepo;
import com.statvital.StatVital.dtos.request.SignInMorgueRequest;
import com.statvital.StatVital.dtos.request.SignUpMorgueRequest;
import com.statvital.StatVital.dtos.response.LogInAdminResponse;
import com.statvital.StatVital.dtos.response.LogInMorgueResponse;
import com.statvital.StatVital.dtos.response.SignUpMorgueResponse;
import com.statvital.StatVital.exceptions.HospitalAlreadyExist;
import com.statvital.StatVital.exceptions.HospitalNotFound;
import com.statvital.StatVital.exceptions.IncorrectCredentials;
import com.statvital.StatVital.exceptions.MorgueAlreadyExist;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.statvital.StatVital.utils.MorgueMapper.mapper;
import static com.statvital.StatVital.utils.MorgueMapper.morgueMap;

@Service
@AllArgsConstructor
public class MorgueServiceImpl implements MorgueService{

    private final MorgueAdminRepo morgueAdminRepo;

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


}
