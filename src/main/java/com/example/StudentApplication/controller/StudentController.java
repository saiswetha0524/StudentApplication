package com.example.StudentApplication.controller;

import com.example.StudentApplication.entities.Student;
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
    public String hello() {

        return serviceHelper.helloMethod();
    }

    @GetMapping("/")
    public APIResponse<List<Student>> getAllStudents() {

        return serviceHelper.findall();
    }

    //TODO: Implement RESPONSE return type to all the endpoints

    @GetMapping("/{stud_id}")

    public ResponseEntity<APIResponse<Student>> getStudentById(@PathVariable int stud_id) {

        return serviceHelper.findById(stud_id);
    }

    @PostMapping("/")
    public APIResponse<Student> addStudent(@RequestBody Student stud) {

        return serviceHelper.addStudent(stud);
    }

    @DeleteMapping("/{stud_id}")
    public APIResponse<Student> removeStudent(@PathVariable int stud_id) {

        return serviceHelper.deleteStudent(stud_id);
    }

    @DeleteMapping("/")
    public APIResponse<Student> removeAllStudents() {

        return serviceHelper.deleteAllStudents();
    }
}
