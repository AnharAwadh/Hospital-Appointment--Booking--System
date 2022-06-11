package com.example.hospitalappointmentbookingsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Data@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Patient  {

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




    //Relation

   @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL)
   @JsonIgnore
    private Set<Appointment> appointments;

    @OneToOne(cascade  = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;


}
