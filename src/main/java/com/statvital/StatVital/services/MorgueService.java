package com.statvital.StatVital.services;

import com.statvital.StatVital.data.model.Death;
import com.statvital.StatVital.dtos.request.DeathReq;
import com.statvital.StatVital.dtos.request.SignInMorgueRequest;
import com.statvital.StatVital.dtos.request.SignUpMorgueRequest;
import com.statvital.StatVital.dtos.response.LogInMorgueResponse;
import com.statvital.StatVital.dtos.response.RegisterDeathResponse;
import com.statvital.StatVital.dtos.response.SignUpMorgueResponse;

import java.util.List;
import java.util.Optional;

public interface MorgueService {
    SignUpMorgueResponse signup (SignUpMorgueRequest request);

    LogInMorgueResponse loginMorgue (SignInMorgueRequest request);
    RegisterDeathResponse registerBody(DeathReq deathReq);

    Optional<?> searchDeath (String name);
    List<Death> getData();

}
