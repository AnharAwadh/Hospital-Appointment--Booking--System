package com.example.hospitalappointmentbookingsystem.controller;

import com.example.hospitalappointmentbookingsystem.DTO.Api;
import com.example.hospitalappointmentbookingsystem.DTO.RegisterPatientDto;
import com.example.hospitalappointmentbookingsystem.Utils;
import com.example.hospitalappointmentbookingsystem.model.Account;
import com.example.hospitalappointmentbookingsystem.model.Doctor;
import com.example.hospitalappointmentbookingsystem.model.Patient;
import com.example.hospitalappointmentbookingsystem.repostory.PatientRepository;
import com.example.hospitalappointmentbookingsystem.serivce.PatientService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/patient")
@RequiredArgsConstructor
public class PatientController {
    Logger logger= LoggerFactory.getLogger(PatientController.class);
    private final PatientService patientService;

    @GetMapping("/get-patient")
    public ResponseEntity<Patient> getPatient(){
        logger.info("Get Patient");
        Account account = Utils.getAccount(SecurityContextHolder.getContext());
        return ResponseEntity.status(200).body(patientService.getPatientByEmail(account.getEmail()));
    }



    @PostMapping("/register-patient")
    public ResponseEntity<Api> addPatient(@RequestBody @Valid RegisterPatientDto registerPatientDto){
        logger.info("Register Patient");
      patientService.addPatient(registerPatientDto.getAccount(),registerPatientDto.getPatient());
        return ResponseEntity.status(201).body(new Api("Patient added",201));


    }
    @PutMapping("/update-patient")
    public ResponseEntity<Patient> updatePatient(@RequestBody @Valid Patient patient){
        logger.info("Update Patient");
        Account account = Utils.getAccount(SecurityContextHolder.getContext());
        Patient currentPatient = patientService.getPatientByEmail(account.getEmail());
        return ResponseEntity.status(200).body(patientService.updatePatient(patient,currentPatient));
    }
    @DeleteMapping("/delete-patient")
    public ResponseEntity deletePatient(){
        logger.info("delete Patient");
        Account account = Utils.getAccount(SecurityContextHolder.getContext());
        Patient patient = patientService.getPatientByEmail(account.getEmail());
        patientService.deletePatient(account,patient);
        return ResponseEntity.status(200).body("The Patient deleted");

    }


    @DeleteMapping("/delete-comment/{id}")
    public ResponseEntity deleteCommentByPatient(@PathVariable Integer id){
        logger.info("Delete Comment By Patient ");
       patientService.deleteCommentByPatient(id);
        return ResponseEntity.status(200).body("The comment deleted");

    }
    @GetMapping("get-rate/{doctorid}")
    public ResponseEntity getRate(@PathVariable Integer doctorId){
        logger.info("Get rate about Doctor");
        return ResponseEntity.status(200).body(patientService.getrate(doctorId));}
}
