package com.example.hospitalappointmentbookingsystem.repostory;

import com.example.hospitalappointmentbookingsystem.model.Appointment;
import com.example.hospitalappointmentbookingsystem.model.Doctor;
import com.example.hospitalappointmentbookingsystem.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository <Appointment,Integer>{

    List<Appointment> findAppointmentsByPatientId(Integer patientId);
    List<Appointment> findAppointmentsByDoctorId(Integer doctorId);

    List<Appointment> findByDoctor_IdAndStatusIn(Integer doctorId,String[] status);
    Optional<Appointment> findByIdAndAndPatientAndStatus(Integer id, Patient patient, String status);
}
