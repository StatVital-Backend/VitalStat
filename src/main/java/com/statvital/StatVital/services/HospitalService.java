package com.statvital.StatVital.services;

import com.statvital.StatVital.data.model.HospitalAdmin;
import com.statvital.StatVital.dtos.request.SignInHospitalRequest;
import com.statvital.StatVital.dtos.request.SignUpHospitalAdminRequest;
import com.statvital.StatVital.dtos.response.LogInAdminResponse;
import com.statvital.StatVital.dtos.response.SignInHospitalAdminResponse;

public interface HospitalService {
    SignInHospitalAdminResponse signin(SignUpHospitalAdminRequest request);

    LogInAdminResponse logIn(SignInHospitalRequest request);
}
