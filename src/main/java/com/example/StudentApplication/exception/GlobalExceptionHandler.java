package com.example.StudentApplication.exception;

import com.example.StudentApplication.entities.Student;
import com.example.StudentApplication.models.APIError;
import com.example.StudentApplication.models.APIResponse;
import com.example.StudentApplication.models.StatusEnum;
import com.example.StudentApplication.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    StudentService studentService;

    private static final Logger logger= LoggerFactory.getLogger(StudentService.class);


    @ExceptionHandler({StudentNotFoundException.class})
    public ResponseEntity<Object> handleStudentNotFoundException(StudentNotFoundException snf, HttpServletRequest request) {
        String requestURI= String.valueOf(request.getRequestURL());
        APIError<Student> apiErrorResponse = new APIError<>();
        apiErrorResponse.setErrorMessage(snf.getMessage());
        apiErrorResponse.setStatus(StatusEnum.FAILURE);
        apiErrorResponse.setRequestURI(requestURI);
        logger.error("StudentNotFoundException is handled");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiErrorResponse);
    }

    @ExceptionHandler({StudentAlreadyExistsException.class})
    public ResponseEntity<Object> handleStudentAlreadyExistsException(StudentAlreadyExistsException sae, HttpServletRequest request) {
        String requestURI= String.valueOf(request.getRequestURL());
        APIError<Student> apiErrorResponse = new APIError<>();
        apiErrorResponse.setErrorMessage(sae.getMessage());
        apiErrorResponse.setStatus(StatusEnum.FAILURE);
        apiErrorResponse.setRequestURI(requestURI);
        logger.error("StudentAlreadyExistsException is handled");
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(apiErrorResponse);
    }

    @ExceptionHandler({StudentsPartiallyDeletedException.class})
    public ResponseEntity<Object> handleStudentsPartiallyDeletedException(StudentsPartiallyDeletedException spde,HttpServletRequest request) {
        String requestURI= String.valueOf(request.getRequestURL());
        APIError<Student> apiErrorResponse = new APIError<>();
        apiErrorResponse.setErrorMessage(spde.getMessage());
        apiErrorResponse.setStatus(StatusEnum.FAILURE);
        apiErrorResponse.setRequestURI(requestURI);
        logger.error("StudentsPartiallyDeletedException is handled");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiErrorResponse);
    }

}
