package com.example.hospitalappointmentbookingsystem;

import com.example.hospitalappointmentbookingsystem.model.Account;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class Utils {
    public static Account getAccount(SecurityContext context) {
        Account account = (Account) context.getAuthentication().getPrincipal();
        return account;
    }
}
