package com.statvital.StatVital.data.repository;

import com.statvital.StatVital.data.model.Child;
import com.statvital.StatVital.data.model.Death;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeathRepo extends JpaRepository<Death, Long> {
    Optional<Death> findChildBBodyByDeceasedName(String name);


}
