package com.example.hospitalappointmentbookingsystem.serivce;

import com.example.hospitalappointmentbookingsystem.excption.InvalidIdException;
import com.example.hospitalappointmentbookingsystem.model.*;
import com.example.hospitalappointmentbookingsystem.repostory.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final CommentRepository commentRepository;


    public Admin getAdminByEmail(String email) {
        return adminRepository.findByAccount_Email(email);
    }
    public void addAdmin(Account account, Admin admin){
        String hashedPassword=new BCryptPasswordEncoder().encode(account.getPassword());
        admin.setAccount(account);
        account.setPassword(hashedPassword);

        adminRepository.save(admin);
    }

    public Admin updateAdmin(Admin admin, Admin currentAdmin){

       currentAdmin.setFirstName(admin.getFirstName());
        currentAdmin.setLastName(admin.getLastName());
        currentAdmin.setGender(admin.getGender());
        currentAdmin.setPhoneNumber(admin.getPhoneNumber());
        return adminRepository.save(currentAdmin);

    }

    public void deleteCommentByAdmin(Integer commentId){
       Comment comment=commentRepository.findById(commentId).
                orElseThrow(()->
                        new InvalidIdException("ID invild"));

        commentRepository.delete(comment);


    }


}
