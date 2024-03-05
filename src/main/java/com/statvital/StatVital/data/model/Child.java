package com.statvital.StatVital.data.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    private String fatherName;

    private String motherName;

    private String dob;
    private String sex;
    private String referenceId;

}
