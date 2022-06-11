package com.example.hospitalappointmentbookingsystem.DTO;

import com.example.hospitalappointmentbookingsystem.model.Account;
import com.example.hospitalappointmentbookingsystem.model.Doctor;
import com.example.hospitalappointmentbookingsystem.model.Patient;
import lombok.Data;

@Data
public class RegisterDoctorDto {
    private Doctor doctor;
    private Account account;
}
