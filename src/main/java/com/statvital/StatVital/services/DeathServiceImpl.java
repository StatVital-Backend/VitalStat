package com.statvital.StatVital.services;

import com.statvital.StatVital.data.model.Death;
import com.statvital.StatVital.data.repository.DeathRepo;
import com.statvital.StatVital.dtos.request.DeathReq;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.statvital.StatVital.utils.MorgueMapper.mapDeath;


@Service
@AllArgsConstructor
public class DeathServiceImpl implements DeathService{

    private DeathRepo deathRepo;
    @Override
    public void registerDeath(DeathReq deathReq) {
        Death death = mapDeath(deathReq);
        Death saveDeathInfo = deathRepo.save(death);
        System.out.println(saveDeathInfo);
    }

    @Override
    public Optional<Death> findBody(String name) {
        return deathRepo.findChildBBodyByDeceasedName(name);
    }
}
