package com.statvital.StatVital.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Entity
public class Death {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String receiver;
    private String deceasedName;
    private LocalDateTime dateTime;
    private String causeOfDeath;
    private String broughtBy;
    private String placeOfDeath;
    private String age;
    private String gender;
    private String status;
    private String occupation;

}
