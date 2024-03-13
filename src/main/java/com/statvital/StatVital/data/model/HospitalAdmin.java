package com.statvital.StatVital.data.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Entity
@Service
public class HospitalAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Email
    private String email;
    private String username;


    @Size(min = 8, message = """
    Password must be at least 8 Characters long.
    Password must include Alphanumeric.
""")
    private String password;
    private String facilityName;
    private String facilityLocation;
    private String phoneNumber;
    private boolean isLoggedIn;
    private boolean isEnabled;



}
