package com.example.StudentApplication.models;

import com.example.StudentApplication.entities.Student;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {

    private StatusEnum status;

    private Student response;

    private String errorDetails;

}
