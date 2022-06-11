package com.example.hospitalappointmentbookingsystem.repostory;

import com.example.hospitalappointmentbookingsystem.model.Appointment;
import com.example.hospitalappointmentbookingsystem.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository <Comment,Integer>{


    List<Comment> findAllByDoctorId(Integer doctorId);

}
