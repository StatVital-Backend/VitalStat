package com.statvital.StatVital.data.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@Entity
public class Death {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String receiver;
    private String deceasedName;
    private LocalDate dateTime;
    private String causeOfDeath;
    private String broughtBy;
    private String placeOfDeath;
    private String age;
    private String gender;
    private String status;
    private String occupation;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private MorgueAdmin morgueAdmin;

}
