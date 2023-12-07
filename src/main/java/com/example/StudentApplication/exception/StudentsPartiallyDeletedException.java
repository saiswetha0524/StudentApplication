package com.example.StudentApplication.exception;

public class StudentsPartiallyDeletedException extends RuntimeException{
    public StudentsPartiallyDeletedException(String message){
        super(message);
    }
}
