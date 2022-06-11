package com.example.hospitalappointmentbookingsystem.repostory;

import com.example.hospitalappointmentbookingsystem.model.Doctor;
import com.example.hospitalappointmentbookingsystem.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository <Patient,Integer>{

    Patient findByAccount_Email(String email);
}
