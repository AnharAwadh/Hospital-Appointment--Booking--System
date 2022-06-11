package com.example.hospitalappointmentbookingsystem.serivce;

import com.example.hospitalappointmentbookingsystem.DTO.CreateAppointmentDto;
import com.example.hospitalappointmentbookingsystem.excption.InvalidIdException;
import com.example.hospitalappointmentbookingsystem.model.Appointment;
import com.example.hospitalappointmentbookingsystem.model.Doctor;
import com.example.hospitalappointmentbookingsystem.model.Patient;
import com.example.hospitalappointmentbookingsystem.repostory.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    private final DoctorService doctorService;
    public List<Appointment> getAppointmentByyPatientId(Integer patientId){
        return appointmentRepository.findAppointmentsByPatientId(patientId);
    }

    public List<Appointment> getAppointmentByyDoctorId(Integer doctorId){
        return appointmentRepository.findAppointmentsByDoctorId(doctorId);
    }



    public void createAppointment(CreateAppointmentDto createAppointmentDto) {
        long days = ChronoUnit.DAYS.between(createAppointmentDto.getStartDate(), createAppointmentDto.getEndDate());
        Doctor doctor = doctorService.getDoctorById(createAppointmentDto.getDoctorId());
        for (int i=0;i < days + 1; i++) {
            Integer  currentPeriod = createAppointmentDto.getPeriod();
            LocalTime startTime = LocalTime.of(createAppointmentDto.getStartTime(),0);
            Appointment appointment = createAppointment( doctor, createAppointmentDto.getPeriod(),
                    startTime, createAppointmentDto.getStartDate().plusDays(i));
            appointmentRepository.save(appointment);
            while (true) {
                LocalTime localTime = startTime.plusMinutes(currentPeriod);
                if(localTime.getHour() == createAppointmentDto.getEndTime()) {
                    break;
                }
                Appointment nextAppointment = createAppointment(doctor, createAppointmentDto.getPeriod(),
                        localTime, createAppointmentDto.getStartDate().plusDays(i));
                appointmentRepository.save(nextAppointment);
                currentPeriod = currentPeriod + createAppointmentDto.getPeriod();
            }
        }
    }

    private Appointment createAppointment(Doctor doctor,
                                          Integer period,
                                          LocalTime startTime,
                                          LocalDate localDate) {
        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPeriod(period);
        appointment.setStatus("available");
        appointment.setTime(startTime);
        appointment.setLocalDate(localDate);
        return appointment;
    }

    public List<Appointment> getAvailableAppointment(Integer did) {
        return appointmentRepository.findByDoctor_IdAndStatusIn(did,new String[] {"available","canceled"});
    }

    public void bookAppointment(Integer appointmentId,Patient patient) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() -> new InvalidIdException("invalid appointment"));
        if(appointment.getStatus() == "booked") {
            throw new RuntimeException("appointment already booked");
        }
        appointment.setStatus("booked");
        appointment.setPatient(patient);
        appointmentRepository.save(appointment);
    }


    public void canceledAppointment(Integer appointmentId, Patient patient) {
        Appointment appointment = appointmentRepository.findByIdAndAndPatientAndStatus(appointmentId,patient,"booked").orElseThrow(() -> new InvalidIdException("invalid appointment"));
        appointment.setStatus("canceled");
        appointment.setPatient(patient);
        appointmentRepository.save(appointment);
    }
}
