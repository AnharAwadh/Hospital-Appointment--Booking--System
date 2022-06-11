package com.example.hospitalappointmentbookingsystem.DTO;

import com.example.hospitalappointmentbookingsystem.model.Account;
import com.example.hospitalappointmentbookingsystem.model.Patient;
import lombok.Data;

@Data
public class RegisterPatientDto {
    private Patient patient;
    private Account account;
}
