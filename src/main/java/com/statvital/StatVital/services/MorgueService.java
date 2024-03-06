package com.statvital.StatVital.services;

import com.statvital.StatVital.data.model.Death;
import com.statvital.StatVital.dtos.request.DeathReq;
import com.statvital.StatVital.dtos.request.SignInMorgueRequest;
import com.statvital.StatVital.dtos.request.SignUpMorgueRequest;
import com.statvital.StatVital.dtos.response.LogInMorgueResponse;
import com.statvital.StatVital.dtos.response.RegisterDeathResponse;
import com.statvital.StatVital.dtos.response.SignUpMorgueResponse;

public interface MorgueService {
    SignUpMorgueResponse signup (SignUpMorgueRequest request);

    LogInMorgueResponse loginMorgue (SignInMorgueRequest request);
    RegisterDeathResponse registerBody(DeathReq deathReq);

    Death searchDeath (String name);
}
