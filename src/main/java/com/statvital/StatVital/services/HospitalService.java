package com.statvital.StatVital.services;

import com.statvital.StatVital.data.model.Child;
import com.statvital.StatVital.dtos.request.*;
import com.statvital.StatVital.dtos.response.LogInAdminResponse;
import com.statvital.StatVital.dtos.response.RegisterChildResponse;
import com.statvital.StatVital.dtos.response.SignInHospitalAdminResponse;

public interface HospitalService {
    SignInHospitalAdminResponse signup(SignUpHospitalAdminRequest request);

    LogInAdminResponse logIn(SignInHospitalRequest request);

    RegisterChildResponse registerChild(ChildRequest childRequest);

    String deleteChildInfo(DeleteChildReq deleteChildReq);
//    Boolean verifyToken(String token);

    Child searchChild(String searchChildReq);

    Child updateChildInfo(UpdateChildReq updateChildReq);
}
