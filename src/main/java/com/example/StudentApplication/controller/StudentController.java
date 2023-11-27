package com.example.StudentApplication.controller;

import com.example.StudentApplication.entities.Student;
import com.example.StudentApplication.exception.CustomNotFoundException;
import com.example.StudentApplication.models.APIResponse;
import com.example.StudentApplication.models.StatusEnum;
import com.example.StudentApplication.repo.StudentRepo;
import com.example.StudentApplication.service.ServiceHelper;
import com.example.StudentApplication.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/studentDetails")
public class StudentController {
    @Autowired
    ServiceHelper serviceHelper;

    @GetMapping("/hi")
    public ResponseEntity<String> hello() {
        return ResponseEntity.status(HttpStatus.OK).body(serviceHelper.helloMethod());
    }

    @GetMapping("/")
    public ResponseEntity<APIResponse<List<Student>>> getAllStudents() {
        return ResponseEntity.status(HttpStatus.OK).body(serviceHelper.findall());
    }

    @GetMapping("/{stud_id}")
    public ResponseEntity<APIResponse<Student>> getStudentById(@PathVariable int stud_id) {
        //TODO: Make one variable.
        /*if (apiResponse.getStatus() == StatusEnum.SUCCESS) {
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);*/

        //TODO: handle the thrown exception from findById
        APIResponse<Student> apiResponse = new APIResponse<>();

        try {
            apiResponse = serviceHelper.findById(stud_id);
        } catch (CustomNotFoundException c) {
             apiResponse.setMessage(c.getMessage());
            apiResponse.setStatus(StatusEnum.FAILURE);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        } /*catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }*/
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }


    @PostMapping("/")
    public ResponseEntity<APIResponse<Student>> addStudent(@RequestBody Student stud) {
        return ResponseEntity.status(HttpStatus.OK).body(serviceHelper.addStudent(stud));

    }

    @DeleteMapping("/{stud_id}")
    public ResponseEntity<APIResponse<Student>> removeStudent(@PathVariable int stud_id) {
        return ResponseEntity.status(HttpStatus.OK).body(serviceHelper.deleteStudent(stud_id));
    }

    @DeleteMapping("/")
    public ResponseEntity<APIResponse<Student>> removeAllStudents() {
        return ResponseEntity.status(HttpStatus.OK).body(serviceHelper.deleteAllStudents());
    }
}