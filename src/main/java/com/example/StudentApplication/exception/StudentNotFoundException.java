package com.example.StudentApplication.exception;


import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class StudentNotFoundException extends RuntimeException{


    public StudentNotFoundException(String msg){
        super(msg);
    }

}
