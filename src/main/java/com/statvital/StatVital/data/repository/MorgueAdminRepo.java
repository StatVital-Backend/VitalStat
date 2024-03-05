package com.statvital.StatVital.data.repository;

import com.statvital.StatVital.data.model.MorgueAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MorgueAdminRepo extends JpaRepository<MorgueAdmin, Long> {
    Optional<MorgueAdmin>findMorgueAdminByFacilityName(String facilityName);
}
