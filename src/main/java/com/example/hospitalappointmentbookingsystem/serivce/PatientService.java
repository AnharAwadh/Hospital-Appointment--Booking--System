package com.example.hospitalappointmentbookingsystem.serivce;

import com.example.hospitalappointmentbookingsystem.excption.InvalidIdException;
import com.example.hospitalappointmentbookingsystem.model.Account;
import com.example.hospitalappointmentbookingsystem.model.Comment;
import com.example.hospitalappointmentbookingsystem.model.Patient;
import com.example.hospitalappointmentbookingsystem.repostory.AccountRepository;
import com.example.hospitalappointmentbookingsystem.repostory.CommentRepository;
import com.example.hospitalappointmentbookingsystem.repostory.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService  {

    private final PatientRepository patientRepository;
    private final CommentRepository commentRepository;
    private final AccountRepository accountRepository;

    public void addPatient(Account account, Patient patient){
        String hashedPassword=new BCryptPasswordEncoder().encode(account.getPassword());
        patient.setAccount(account);
        account.setPassword(hashedPassword);

        patientRepository.save(patient);
    }

    public Patient updatePatient(Patient patient, Patient currentPatient){

        currentPatient.setFirstName(patient.getFirstName());
        currentPatient.setLastName(patient.getLastName());
        currentPatient.setGender(patient.getGender());
        currentPatient.setPhoneNumber(patient.getPhoneNumber());
       return patientRepository.save(currentPatient);

    }
    public Patient getPatientByEmail(String email) {
        return patientRepository.findByAccount_Email(email);
    }
    public void deletePatient(Account account, Patient patient) {
        accountRepository.delete(account);
        patientRepository.delete(patient);
    }


    public void deleteCommentByPatient(Integer commentId){
        Comment comment=commentRepository.findById(commentId).
                orElseThrow(()->
                        new InvalidIdException("ID invild"));

        commentRepository.delete(comment);


    }

    public double getrate(Integer doctorid) {
        //  (Total nunber of star / total number of persons who review * 5 ) * 5

        Integer total = 0;
        List<Comment> comment = commentRepository.findAllByDoctorId(doctorid);
        for (int i = 0; i < comment.size(); i++) {
            Comment currentcomment = comment.get(i);
            Integer rate = currentcomment.getRate();
            total += rate;
        }
        if (total == 0) {
            return 0.0;
        }
        return total / comment.size();
    }



}


