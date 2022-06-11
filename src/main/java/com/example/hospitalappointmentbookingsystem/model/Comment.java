package com.example.hospitalappointmentbookingsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id ;
    @NotEmpty(message = " Message required")
    @Size(min = 6,message = " Message should be 6 character or more")
    private String message;
    @NotNull(message = " Rate required")
    @Min(value = 1,message = "min value mast be more then 0")
    @Max(value = 5,message = "max value mast less then 6")
    private Integer rate;


    //Relation
    @ManyToOne
  //  @JoinColumn(name ="patient_id")
    private Patient patient;
    @ManyToOne
  // @JoinColumn(name ="doctor_id")
    private Doctor doctor;
}
