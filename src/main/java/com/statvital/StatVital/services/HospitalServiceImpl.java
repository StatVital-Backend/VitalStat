package com.statvital.StatVital.services;

import com.statvital.StatVital.data.model.HospitalAdmin;
import com.statvital.StatVital.data.repository.HospitalAdminRepo;
import com.statvital.StatVital.dtos.request.SignInHospitalRequest;
import com.statvital.StatVital.dtos.request.SignUpHospitalAdminRequest;
import com.statvital.StatVital.dtos.response.LogInAdminResponse;
import com.statvital.StatVital.dtos.response.SignInHospitalAdminResponse;
import com.statvital.StatVital.exceptions.AdminNotFoundException;
import com.statvital.StatVital.exceptions.HospitalAlreadyExist;
import com.statvital.StatVital.exceptions.HospitalNotFound;
import com.statvital.StatVital.exceptions.IncorrectCredentials;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.statvital.StatVital.utils.Mapper.hospitalMapper;
import static com.statvital.StatVital.utils.Mapper.responseMapper;

@Service
@AllArgsConstructor
public class HospitalServiceImpl implements HospitalService{
    private final HospitalAdminRepo hospitalAdminRepo;

    @Override
    public SignInHospitalAdminResponse signup(SignUpHospitalAdminRequest request) {
        findHospital(request.getFacilityName());
        return responseMapper(hospitalAdminRepo.save(hospitalMapper(request)));
    }

    @Override
    public LogInAdminResponse logIn(SignInHospitalRequest request) {
        HospitalAdmin hospitalAdmin = getAdmin(request.getFacilityName());
        validatePassword(request, hospitalAdmin);

        hospitalAdmin.setLoggedIn(true);
        HospitalAdmin hospitalAdmin1 = hospitalAdminRepo.save(hospitalAdmin);

        LogInAdminResponse logInAdminResponse = new LogInAdminResponse();
        logInAdminResponse.setMessage("Login Successful");
        logInAdminResponse.setLoggedIn(hospitalAdmin1.isLoggedIn());

        return logInAdminResponse;

    }

    private void validatePassword(SignInHospitalRequest request, HospitalAdmin admin) {
        if(!admin.getPassword().equals(request.getPassword()))
            throw  new IncorrectCredentials("Incorrect Username or password");
    }

    private HospitalAdmin getAdmin(String facilityName) {
        Optional<HospitalAdmin> admin =hospitalAdminRepo.findHospitalAdminByFacilityName(facilityName);
        if(admin.isEmpty()) throw new HospitalNotFound("Hospital Does not exist");
        return admin.get();
    }

//    private Optional<HospitalAdmin> retrieveAdmin(String email) {
//        Optional<HospitalAdmin> newAdmin = retrieveAdmin(email);
//        return null;
//    }

    private void findHospital(String facilityName) {
        Optional<HospitalAdmin> admin = hospitalAdminRepo.findHospitalAdminByFacilityName(facilityName);
        if(admin.isPresent())
            throw new HospitalAlreadyExist("Hospital Already Exist");
    }


}
