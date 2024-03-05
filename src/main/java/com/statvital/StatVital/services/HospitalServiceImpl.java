package com.statvital.StatVital.services;

//import com.statvital.StatVital.data.model.Confirmation;
import com.statvital.StatVital.data.model.Child;
import com.statvital.StatVital.data.model.HospitalAdmin;
//import com.statvital.StatVital.data.repository.ConfirmationRepo;
//import com.statvital.StatVital.data.repository.ConfirmationRepo;
import com.statvital.StatVital.data.repository.ChildRepository;
import com.statvital.StatVital.data.repository.HospitalAdminRepo;
import com.statvital.StatVital.dtos.request.*;
import com.statvital.StatVital.dtos.response.LogInAdminResponse;
import com.statvital.StatVital.dtos.response.RegisterChildResponse;
import com.statvital.StatVital.dtos.response.SignInHospitalAdminResponse;
import com.statvital.StatVital.exceptions.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

//import static com.statvital.StatVital.data.repository.ChildRepository.children;
//import static com.statvital.StatVital.data.repository.HospitalAdminRepo.children;
import static com.statvital.StatVital.utils.Mapper.*;

@Service
@AllArgsConstructor
public class HospitalServiceImpl implements HospitalService{
    private final HospitalAdminRepo hospitalAdminRepo;
    private final ChildRepository childRepository;
    private final ChildService childService;
    private final JwtTokenImpl jwtToken;
//    private final ConfirmationRepo confirmationRepo;

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

        //call jwt

        LogInAdminResponse logInAdminResponse = new LogInAdminResponse();
        logInAdminResponse.setMessage("Login Successful");
        logInAdminResponse.setLoggedIn(hospitalAdmin1.isLoggedIn());
        logInAdminResponse.setLogInDate(String.valueOf(LocalDateTime.now()));

        return logInAdminResponse;

    }

    @Override
    public RegisterChildResponse registerChild(ChildRequest childRequest) {
        findRegisteredChild(childRequest.getId());

        childService.registerChild(childRequest);
        RegisterChildResponse response = new RegisterChildResponse();
        response.setMessage("Child Registered Successfully");
        response.setDateRegistered(LocalDateTime.now());
        response.setId(generateReferenceId());

        return response;
    }

    private void findRegisteredChild(String referenceId) {
        Optional<Child>child = childRepository.findChildById(referenceId);
        if(child.isPresent()) throw new ChildExist("Child Already Exist");
    }

    @Override
    public String deleteChildInfo(DeleteChildReq deleteChildReq) {
        childService.deleteProfile(deleteChildReq);
        return null;
    }

    @Override
    public Child searchChild(String name) {
        Optional<Child> child = childService.findChild(name);
        if (child.isEmpty()) throw new ChildNotFound("Child not found");
        return child.get();
    }


//    @Override
//    public Boolean verifyToken(String token) {
//        Confirmation confirmation = confirmationRepo.findByToken(token);
//        Optional<HospitalAdmin> hospitalAdmin = hospitalAdminRepo
//                .findHospitalAdminByEmailIgnoreCase(confirmation.getHospitalAdmin().getEmail());
//        hospitalAdmin.get().setEnabled(true);
//        hospitalAdminRepo.save(new HospitalAdmin());
//        return Boolean.TRUE;

//        if (hospitalAdmin.isPresent()) {
//            HospitalAdmin hospitalAdmin = new HospitalAdmin();
//            hospitalAdminRepo.save(hospitalAdmin);
//
//            return Boolean.TRUE;
//        } else {
//            return Boolean.FALSE;
//        }
//    }

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
