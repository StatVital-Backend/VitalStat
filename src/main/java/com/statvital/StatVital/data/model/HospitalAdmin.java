package com.statvital.StatVital.data.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class HospitalAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String username;
    private String password;
    private String facilityName;
    private String facilityLocation;
    private String phoneNumber;
    private boolean isLoggedIn;
    private boolean isEnabled;



}
