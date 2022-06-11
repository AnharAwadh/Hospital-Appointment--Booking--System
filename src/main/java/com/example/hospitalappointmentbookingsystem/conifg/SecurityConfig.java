package com.example.hospitalappointmentbookingsystem.conifg;

import com.example.hospitalappointmentbookingsystem.serivce.AccountDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AccountDetails accountDetails;

    public SecurityConfig(AccountDetails accountDetails) {
        this.accountDetails = accountDetails;
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountDetails).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(passwordEncoder());
//        provider.setUserDetailsService(patientDetliseService);
//        return provider;
//    }




    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/api/v1/patient/register-patient").permitAll()
                .antMatchers("api/v1/admin/register-admin").permitAll()
                .antMatchers("api/v1/doctor/register-doctor").permitAll()
                .antMatchers("/api/v1/admin/**").hasRole("ADMIN")
                .antMatchers("api/v1/doctor/**").hasRole("DOCTOR")
                .antMatchers("/api/v1/patient/**").hasRole("PATIENT")
                .anyRequest().authenticated()
                .and().httpBasic();

    }


}
