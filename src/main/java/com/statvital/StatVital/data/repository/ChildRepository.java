package com.statvital.StatVital.data.repository;

import com.statvital.StatVital.data.model.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {
    Optional<Child> findChildByName(String name);

    Optional<Child> findChildByReferenceId(String referenceId);

    List<Child>findChildByNameIsContaining(String name);

}
