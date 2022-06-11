package com.example.hospitalappointmentbookingsystem.controller;

import com.example.hospitalappointmentbookingsystem.DTO.Api;
import com.example.hospitalappointmentbookingsystem.DTO.CreateAppointmentDto;
import com.example.hospitalappointmentbookingsystem.Utils;
import com.example.hospitalappointmentbookingsystem.model.Account;
import com.example.hospitalappointmentbookingsystem.model.Appointment;
import com.example.hospitalappointmentbookingsystem.model.Patient;
import com.example.hospitalappointmentbookingsystem.serivce.AppointmentService;
import com.example.hospitalappointmentbookingsystem.serivce.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@RequestMapping("api/v1/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final PatientService patientService;


    @PostMapping("/addappointment/{appid}")
    public ResponseEntity<Api> addAppointment(@PathVariable Integer appid){
        Account account = Utils.getAccount(SecurityContextHolder.getContext());
        Patient patient = patientService.getPatientByEmail(account.getEmail());
       appointmentService.bookAppointment(appid,patient);
        return ResponseEntity.status(201).body(new Api(" Appointment booked",201));

    }

    @GetMapping("/get-appointment-patient/{did}")
    public ResponseEntity getAppointment(@PathVariable Integer did){

        return ResponseEntity.status(201).body(appointmentService.getAvailableAppointment(did));
    }
    @DeleteMapping("/canceled-appointment/{appid}")
    public ResponseEntity canceledAppointmentByPatient(@PathVariable Integer appid){
        Account account = Utils.getAccount(SecurityContextHolder.getContext());
        Patient patient = patientService.getPatientByEmail(account.getEmail());
        appointmentService.canceledAppointment(appid,patient);
        return ResponseEntity.status(200).body("The Appointment canceled");

    }
    @GetMapping("/get-appointment-doctor/{did}")
    public ResponseEntity getAppointmentByDoctor(@PathVariable Integer did){
        return ResponseEntity.status(201).body(appointmentService.getAppointmentByyDoctorId(did));

    }
    @PostMapping("/creat-appointment")
    public ResponseEntity creatAppointment(@RequestBody CreateAppointmentDto createAppointmentDto){

        appointmentService.createAppointment(createAppointmentDto);

        return ResponseEntity.status(200).body("Appointment added");
    }
    @GetMapping("/get-appontment-by-patient/{pid}")
    public ResponseEntity getAppointmentByPatient(@PathVariable Integer pid){

        return ResponseEntity.status(201).body(appointmentService.getAppointmentByyPatientId(pid));

    }
}
