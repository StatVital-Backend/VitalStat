//package com.statvital.StatVital.data.model;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.data.annotation.CreatedDate;
//
//import java.time.LocalDateTime;
//import java.util.UUID;
//
//@Data
//@NoArgsConstructor
//@Entity
//@Table(name = "confirmation")
//public class Confirmation {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//    private String token;
//
//    @CreatedDate
//    private LocalDateTime dateCreated;
//    @OneToOne(targetEntity = HospitalAdmin.class, fetch = FetchType.EAGER)
//    @JoinColumn(nullable = false, name = "admin_id")
//    private HospitalAdmin hospitalAdmin;
//
//
//
//    public Confirmation(HospitalAdmin hospitalAdmin) {
//        this.token = UUID.randomUUID().toString();
//        this.dateCreated = LocalDateTime.now();
//        this.hospitalAdmin = hospitalAdmin;
//    }
//}
