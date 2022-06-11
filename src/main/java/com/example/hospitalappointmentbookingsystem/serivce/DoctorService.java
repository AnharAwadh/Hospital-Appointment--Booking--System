package com.example.hospitalappointmentbookingsystem.serivce;

import com.example.hospitalappointmentbookingsystem.excption.InvalidIdException;
import com.example.hospitalappointmentbookingsystem.model.Account;
import com.example.hospitalappointmentbookingsystem.model.Doctor;
import com.example.hospitalappointmentbookingsystem.repostory.AccountRepository;
import com.example.hospitalappointmentbookingsystem.repostory.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    private final AccountRepository accountRepository;


    public Doctor getDoctorByEmail(String email) {
        return doctorRepository.findByAccount_Email(email);
    }

    public Doctor getDoctorById(Integer id) {
        return doctorRepository.findById(id).orElseThrow(() -> new InvalidIdException("ID invild"));
    }

    public void addDoctor(Account account, Doctor doctor) {
        String hashedPassword = new BCryptPasswordEncoder().encode(account.getPassword());
        doctor.setAccount(account);
        account.setPassword(hashedPassword);

        doctorRepository.save(doctor);
    }


    public Doctor updateDoctor(Doctor doctor, Doctor currentDoctor) {

        currentDoctor.setFirstName(doctor.getFirstName());
        currentDoctor.setLastName(doctor.getLastName());
        currentDoctor.setGender(doctor.getGender());
        currentDoctor.setPhoneNumber(doctor.getPhoneNumber());
        return doctorRepository.save(currentDoctor);


    }

    public void deleteDoctor(Account account, Doctor doctor) {
        accountRepository.delete(account);
        doctorRepository.delete(doctor);
    }
}