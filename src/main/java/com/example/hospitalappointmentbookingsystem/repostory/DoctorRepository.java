package com.example.hospitalappointmentbookingsystem.repostory;

import com.example.hospitalappointmentbookingsystem.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository <Doctor,Integer>{

    Doctor findByAccount_Email(String email);
}
