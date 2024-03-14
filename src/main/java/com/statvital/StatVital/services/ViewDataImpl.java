//package com.statvital.StatVital.services;
//
//import com.statvital.StatVital.data.model.HospitalAdmin;
//import com.statvital.StatVital.data.repository.HospitalAdminRepo;
//import com.statvital.StatVital.data.repository.ViewDataRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ViewDataImpl implements ViewData{
//    @Autowired
//    private ViewDataRepo viewDataRepo;
//    @Autowired
//    private HospitalAdminRepo hospitalAdminRepo;
//
//    @Override
//    public List<HospitalAdmin> getAllData() {
//        return hospitalAdminRepo.findAll();
//    }
//}
