package com.statvital.StatVital.data.repository;

import com.statvital.StatVital.data.model.Child;
import com.statvital.StatVital.data.model.Death;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalDeathRepo extends JpaRepository<Death, Long> {
    List<Death>findDeathByDeceasedName(String name);
}
