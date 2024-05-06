package com.statvital.StatVital.data.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    private String fatherName;

    private String motherName;

    private LocalDate dob;
    private String sex;
    private String referenceId;
    private String age;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private HospitalAdmin hospitalAdmin;

}
