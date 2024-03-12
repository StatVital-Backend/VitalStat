package com.statvital.StatVital.services;

import com.statvital.StatVital.data.model.Child;
import com.statvital.StatVital.dtos.request.*;
import com.statvital.StatVital.dtos.response.LogInAdminResponse;
import com.statvital.StatVital.dtos.response.RegisterDeathResponse;
import com.statvital.StatVital.dtos.response.RegisterChildResponse;
import com.statvital.StatVital.dtos.response.SignInHospitalAdminResponse;
import org.springframework.http.ResponseEntity;

public interface HospitalService {
    SignInHospitalAdminResponse signup(SignUpHospitalAdminRequest request);

    LogInAdminResponse logIn(SignInHospitalRequest request);

    RegisterChildResponse registerChild(ChildRequest childRequest);

    RegisterDeathResponse registerBody(DeathReq deathReq);

    String deleteChildInfo(DeleteChildReq deleteChildReq);
//    Boolean verifyToken(String token);

    Child searchChild(SearchChildReq searchChildReq);

    Child updateChildInfo(UpdateChildReq updateChildReq);
}
