package com.example.hospitalappointmentbookingsystem.repostory;

import com.example.hospitalappointmentbookingsystem.model.Admin;
import com.example.hospitalappointmentbookingsystem.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository <Admin,Integer>{

   Admin findByAccount_Email(String email);
}
