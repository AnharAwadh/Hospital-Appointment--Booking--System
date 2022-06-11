package com.example.hospitalappointmentbookingsystem.controller;

import com.example.hospitalappointmentbookingsystem.DTO.Api;
import com.example.hospitalappointmentbookingsystem.DTO.RegisterDoctorDto;
import com.example.hospitalappointmentbookingsystem.DTO.RegisterPatientDto;
import com.example.hospitalappointmentbookingsystem.Utils;
import com.example.hospitalappointmentbookingsystem.model.Account;
import com.example.hospitalappointmentbookingsystem.model.Doctor;
import com.example.hospitalappointmentbookingsystem.model.Patient;
import com.example.hospitalappointmentbookingsystem.serivce.DoctorService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("api/v1/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;
    Logger logger= LoggerFactory.getLogger(DoctorController.class);



    @GetMapping("/get-doctor")
    public ResponseEntity<Doctor> getDoctor(){
        logger.info("Get Doctor");
        Account account = Utils.getAccount(SecurityContextHolder.getContext());
        return ResponseEntity.status(200).body(doctorService.getDoctorByEmail(account.getEmail()));
    }

    @PostMapping("/register-doctor")
    public ResponseEntity<Api> addDoctor(@RequestBody @Valid RegisterDoctorDto registerDoctorDto){
        logger.info("Register Doctor");
        doctorService.addDoctor(registerDoctorDto.getAccount(),registerDoctorDto.getDoctor());
        return ResponseEntity.status(201).body(new Api("Doctor added",201));


    }
    @PutMapping("/update-doctor")
    public ResponseEntity<Doctor> updateDoctor(@RequestBody @Valid Doctor doctor){
        logger.info("Update Doctor");
        Account account = Utils.getAccount(SecurityContextHolder.getContext());
        Doctor currentDoctor = doctorService.getDoctorByEmail(account.getEmail());
        return ResponseEntity.status(200).body(doctorService.updateDoctor(doctor,currentDoctor));
    }
    @DeleteMapping("delete-doctor")
    public ResponseEntity deleteDoctor(){
        logger.info("Delete Doctor");
        Account account = Utils.getAccount(SecurityContextHolder.getContext());
        Doctor doctor = doctorService.getDoctorByEmail(account.getEmail());
        doctorService.deleteDoctor(account,doctor);
        return ResponseEntity.status(200).body("The Doctor deleted");

    }

}
