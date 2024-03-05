package com.statvital.StatVital.data.repository;

import com.statvital.StatVital.data.model.Child;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ChildRepository extends JpaRepository<Child, Long> {
    Optional<Child> findChildByName(String name);

    Optional<Child> findChildByReferenceId(String referenceId);

}
