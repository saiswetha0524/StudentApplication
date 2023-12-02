package com.example.StudentApplication.exception;

import com.example.StudentApplication.entities.Student;
import com.example.StudentApplication.models.APIResponse;
import com.example.StudentApplication.models.StatusEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({StudentNotFoundException.class})
    public ResponseEntity<Object> handleStudentNotFoundException(StudentNotFoundException snf) {
        APIResponse<Student> apiResponse = new APIResponse<>();
        apiResponse.setMessage(snf.getMessage());
        apiResponse.setStatus(StatusEnum.FAILURE);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler({StudentAlreadyExistsException.class})
    public ResponseEntity<Object> handleStudentAlreadyExistsException(StudentAlreadyExistsException sae) {
        APIResponse<Student> apiResponse = new APIResponse<>();
        apiResponse.setMessage(sae.getMessage());
        apiResponse.setStatus(StatusEnum.FAILURE);
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(apiResponse);
    }

}
