package com.example.StudentApplication.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


public class CustomNotFoundException extends RuntimeException{

    public CustomNotFoundException(String msg){
        super(msg);
    }

}
