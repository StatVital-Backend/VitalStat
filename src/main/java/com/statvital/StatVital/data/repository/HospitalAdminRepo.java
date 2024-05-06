package com.statvital.StatVital.data.repository;

import com.statvital.StatVital.data.model.Child;
import com.statvital.StatVital.data.model.HospitalAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface HospitalAdminRepo extends JpaRepository<HospitalAdmin, Long> {
    Optional<HospitalAdmin> findHospitalAdminByFacilityName(String facilityName);
    Optional<HospitalAdmin> findHospitalAdminByEmailIgnoreCase(String Email);



}
