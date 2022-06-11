package com.example.hospitalappointmentbookingsystem.controller;

import com.example.hospitalappointmentbookingsystem.DTO.Api;
import com.example.hospitalappointmentbookingsystem.Utils;
import com.example.hospitalappointmentbookingsystem.model.Account;
import com.example.hospitalappointmentbookingsystem.model.Comment;
import com.example.hospitalappointmentbookingsystem.model.Doctor;
import com.example.hospitalappointmentbookingsystem.model.Patient;
import com.example.hospitalappointmentbookingsystem.serivce.CommentService;
import com.example.hospitalappointmentbookingsystem.serivce.DoctorService;
import com.example.hospitalappointmentbookingsystem.serivce.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final PatientService patientService;



    @PostMapping("/add-comment/{did}")
    public ResponseEntity<Api> addComment(@PathVariable Integer did,@RequestBody @Valid  Comment comment){
        Account account = Utils.getAccount(SecurityContextHolder.getContext());
        Patient patient = patientService.getPatientByEmail(account.getEmail());
        commentService.addCommentByPatient(patient, did, comment);
        return ResponseEntity.status(201).body(new Api("comment   added",201));

}
    @GetMapping("/get-all-comment/{did}")
    public ResponseEntity<List<Comment>> getAllComment(@PathVariable Integer did){


        return ResponseEntity.status(201).body( commentService.getAllCommentByDoctorId(did));

    }

}

