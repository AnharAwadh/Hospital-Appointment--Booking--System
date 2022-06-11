package com.example.hospitalappointmentbookingsystem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate localDate;
    @JsonFormat(pattern="HH:mm:ss")
    private LocalTime time;
    @Pattern(regexp = "booked|available|canceled")
    private String status;
    private Integer period;
    @ManyToOne
   // @JoinColumn(name ="patient_id")
    private Patient patient;
    @ManyToOne
   // @JoinColumn(name ="doctor_id")
    private Doctor doctor;

}
