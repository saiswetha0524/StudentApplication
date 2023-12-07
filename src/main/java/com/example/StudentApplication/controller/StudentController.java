package com.example.StudentApplication.controller;

import com.example.StudentApplication.entities.Student;
import com.example.StudentApplication.exception.GlobalExceptionHandler;
import com.example.StudentApplication.models.APIResponse;
import com.example.StudentApplication.service.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public APIResponse<List<Student>> getAllStudents() {
        return serviceHelper.findall();
    }

    @GetMapping("/{id}")
    public APIResponse<Student> getStudentById(@PathVariable int id) {
        return serviceHelper.findById(id);
    }

    @PostMapping("/")
    public APIResponse<Student> addStudent(@RequestBody Student stud) {
        return serviceHelper.addStudent(stud);

    }

    @DeleteMapping("/{id}")
    public APIResponse<Student> removeStudent(@PathVariable int id) {
        return serviceHelper.deleteStudent(id);
    }

    @DeleteMapping("/")
    public APIResponse<Student> removeAllStudents() {
        return serviceHelper.deleteAllStudents();

    }

    @GetMapping("/count")
    public ResponseEntity<Long> countStudents() {
        long studentCount=serviceHelper.countStudents();
        return ResponseEntity.status(HttpStatus.OK).body(studentCount);
    }
}