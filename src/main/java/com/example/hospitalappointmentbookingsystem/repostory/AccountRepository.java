package com.example.hospitalappointmentbookingsystem.repostory;

import com.example.hospitalappointmentbookingsystem.model.Account;
import com.example.hospitalappointmentbookingsystem.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository <Account,Integer>{

    Optional<Account> findByEmail(String email);
}
