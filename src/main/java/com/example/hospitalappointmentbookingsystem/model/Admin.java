package com.example.hospitalappointmentbookingsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Size(min = 3, message = "Last name too short")
    private String firstName;
    @Size(min = 3, message = "Last name too short")
    private String lastName;
    private String phoneNumber;
    @Pattern(regexp = "Female|male")
    private String gender;

    @OneToOne(cascade  = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;



}
