package com.statvital.StatVital.services;

import com.statvital.StatVital.data.model.Child;
import com.statvital.StatVital.data.model.Death;
import com.statvital.StatVital.dtos.request.DeathReq;

import java.util.Optional;

public interface DeathService {
    void registerDeath(DeathReq deathReq);
    Optional<Death> findBody (String name);

}
