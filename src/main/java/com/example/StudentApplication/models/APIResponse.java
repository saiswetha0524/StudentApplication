package com.example.StudentApplication.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Component
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIResponse<E>  {

    private StatusEnum status;

    private String message;

    private E response;

   // private String requestURI;

    // private Object List<Student>;

 //   private String simple;

}
