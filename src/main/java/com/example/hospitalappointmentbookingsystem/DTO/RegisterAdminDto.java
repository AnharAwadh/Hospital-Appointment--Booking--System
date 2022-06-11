package com.example.hospitalappointmentbookingsystem.DTO;

import com.example.hospitalappointmentbookingsystem.model.Account;
import com.example.hospitalappointmentbookingsystem.model.Admin;
import com.example.hospitalappointmentbookingsystem.model.Doctor;
import lombok.Data;

@Data
public class RegisterAdminDto {
    private Admin admin;
    private Account account;
}
