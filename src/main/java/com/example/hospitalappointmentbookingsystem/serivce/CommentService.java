package com.example.hospitalappointmentbookingsystem.serivce;

import com.example.hospitalappointmentbookingsystem.model.Comment;
import com.example.hospitalappointmentbookingsystem.model.Doctor;
import com.example.hospitalappointmentbookingsystem.model.Patient;
import com.example.hospitalappointmentbookingsystem.repostory.CommentRepository;
import com.example.hospitalappointmentbookingsystem.repostory.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final DoctorService doctorService;

    public void addCommentByPatient(Patient patient, Integer doctorId, Comment comment){
        Doctor newDoctor=doctorService.getDoctorById(doctorId);
        comment.setPatient(patient);
        comment.setDoctor(newDoctor);;
        commentRepository.save(comment);

    }

    public List<Comment> getAllCommentByDoctorId(Integer doctorId){

       return commentRepository.findAllByDoctorId(doctorId);
    }



}
