package com.example.StudentApplication.models;

import com.example.StudentApplication.entities.Student;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIResponse<E>  {

    private StatusEnum status;


    private E response;

    private String message;

    private HttpStatus httpStatus;

    private java.util.List<Student> students;



   // private Object List<Student>;

 //   private String simple;

}
