package com.example.hospitalappointmentbookingsystem.controller;

import com.example.hospitalappointmentbookingsystem.DTO.Api;
import com.example.hospitalappointmentbookingsystem.DTO.RegisterAdminDto;
import com.example.hospitalappointmentbookingsystem.DTO.RegisterPatientDto;
import com.example.hospitalappointmentbookingsystem.Utils;
import com.example.hospitalappointmentbookingsystem.model.Account;
import com.example.hospitalappointmentbookingsystem.model.Admin;
import com.example.hospitalappointmentbookingsystem.model.Doctor;
import com.example.hospitalappointmentbookingsystem.model.Patient;
import com.example.hospitalappointmentbookingsystem.serivce.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;



    @GetMapping("/get-damin")
    public ResponseEntity<Admin> getAdmin(){
        Account account = Utils.getAccount(SecurityContextHolder.getContext());
        return ResponseEntity.status(200).body(adminService.getAdminByEmail(account.getEmail()));
    }


    @PutMapping("/update-admin")
    public ResponseEntity<Admin> updateAdmin(@RequestBody @Valid Admin admin){
        Account account = Utils.getAccount(SecurityContextHolder.getContext());
        Admin currentAdmin = adminService.getAdminByEmail(account.getEmail());
        return ResponseEntity.status(200).body(adminService.updateAdmin(admin,currentAdmin));
    }

    @DeleteMapping("/delete-comment/{id}")
    public ResponseEntity deleteCommentByAdmin(@PathVariable Integer id){

      adminService.deleteCommentByAdmin(id);
        return ResponseEntity.status(200).body("The comment deleted");

    }



}
