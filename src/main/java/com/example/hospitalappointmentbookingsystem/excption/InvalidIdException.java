package com.example.hospitalappointmentbookingsystem.excption;

public class InvalidIdException extends  RuntimeException{

    public InvalidIdException(String massage){

        super(massage);
    }


}
