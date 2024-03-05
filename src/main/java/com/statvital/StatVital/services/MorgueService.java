package com.statvital.StatVital.services;

import com.statvital.StatVital.dtos.request.SignInMorgueRequest;
import com.statvital.StatVital.dtos.request.SignUpMorgueRequest;
import com.statvital.StatVital.dtos.response.LogInMorgueResponse;
import com.statvital.StatVital.dtos.response.SignUpMorgueResponse;

public interface MorgueService {
    SignUpMorgueResponse signup (SignUpMorgueRequest request);

    LogInMorgueResponse loginMorgue (SignInMorgueRequest request);
}
