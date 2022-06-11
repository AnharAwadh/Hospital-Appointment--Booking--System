package com.example.hospitalappointmentbookingsystem.controller;

import com.example.hospitalappointmentbookingsystem.DTO.Api;
import com.example.hospitalappointmentbookingsystem.DTO.RegisterAdminDto;
import com.example.hospitalappointmentbookingsystem.DTO.RegisterDoctorDto;
import com.example.hospitalappointmentbookingsystem.DTO.RegisterPatientDto;
import com.example.hospitalappointmentbookingsystem.serivce.AdminService;
import com.example.hospitalappointmentbookingsystem.serivce.DoctorService;
import com.example.hospitalappointmentbookingsystem.serivce.PatientService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/register")
@RequiredArgsConstructor
public class RegisterController {
    private final AdminService adminService;
    private final PatientService patientService;

    private final DoctorService doctorService;


    Logger logger= LoggerFactory.getLogger(RegisterController.class);


    @PostMapping("admin")
    public ResponseEntity<Api> addAdmin(@RequestBody @Valid RegisterAdminDto registerAdminDto){
        adminService.addAdmin(registerAdminDto.getAccount(),registerAdminDto.getAdmin());
        return ResponseEntity.status(201).body(new Api("Admin added",201));


    }

    @PostMapping("patient")
    public ResponseEntity<Api> addPatient(@RequestBody @Valid RegisterPatientDto registerPatientDto){
        logger.info("Register Patient");
        patientService.addPatient(registerPatientDto.getAccount(),registerPatientDto.getPatient());
        return ResponseEntity.status(201).body(new Api("Patient added",201));
    }

    @PostMapping("doctor")
    public ResponseEntity<Api> addDoctor(@RequestBody @Valid RegisterDoctorDto registerDoctorDto){
        logger.info("Register Doctor");
        doctorService.addDoctor(registerDoctorDto.getAccount(),registerDoctorDto.getDoctor());
        return ResponseEntity.status(201).body(new Api("Doctor added",201));


    }
}
