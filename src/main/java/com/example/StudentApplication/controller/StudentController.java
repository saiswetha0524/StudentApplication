package com.example.StudentApplication.controller;

import com.example.StudentApplication.service.ServiceHelper;
import org.openapitools.api.StudentDetailsApi;
import org.openapitools.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/studentDetails")
public class StudentController implements org.openapitools.api.StudentDetailsApi {
    @Autowired
    ServiceHelper serviceHelper;
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Override
    @PostMapping("/")
    public ResponseEntity<Student> additionOfStuds(@RequestBody Student student) {
       return StudentDetailsApi.super.additionOfStuds(student);
    }

    @Override
    @GetMapping("/count")
    public ResponseEntity<Student> countOfStuds() {
        return StudentDetailsApi.super.countOfStuds();
    }

    @Override
    @DeleteMapping("/")
    public ResponseEntity<Student> delAllStuds() {
        return StudentDetailsApi.super.delAllStuds();
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Student> delStudWithId(Integer id) {
        return StudentDetailsApi.super.delStudWithId(id);
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<Student> getAllStuds() {
        return StudentDetailsApi.super.getAllStuds();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudWithId(Integer id) {
        return StudentDetailsApi.super.getStudWithId(id);
    }

    @Override
    @GetMapping("/hi")
    public ResponseEntity<Student> welcomeAll() {
        return StudentDetailsApi.super.welcomeAll();
    }
/*@GetMapping("/hi")
    public ResponseEntity<String> hello() {
        logger.info("Received GET request at /hi endpoint");
        return ResponseEntity.status(HttpStatus.OK).body(serviceHelper.helloMethod());
    }

    @GetMapping("/")
    public APIResponse<List<Student>> getAllStudents() {
        logger.trace("Received GET request at / endpoint to fetch all students at trace level");
        logger.info("Received GET request at / endpoint to fetch all students at info level");
        return serviceHelper.findall();
    }

    @GetMapping("/{id}")
    public APIResponse<Student> getStudentById(@PathVariable int id) {
        logger.trace("Received GET request at /{} endpoint to fetch student by id at trace level", id);
        logger.info("Received GET request at /{} endpoint to fetch student by id at info level", id);
        return serviceHelper.findById(id);
    }

    @PostMapping("/")
    public APIResponse<Student> addStudent(@RequestBody Student stud) {
        logger.trace("Received POST request at / endpoint to add student: {} at trace level");
        logger.info("Received POST request at / endpoint to add student: {} at info level", stud);
        return serviceHelper.addStudent(stud);

    }

    @DeleteMapping("/{id}")
    public APIResponse<Student> removeStudent(@PathVariable int id) {
        logger.trace("Received DELETE request at /{id} endpoint to remove student by id at trace level", id);
        logger.info("Received DELETE request at /{id} endpoint to remove student by id at info level", id);
        return serviceHelper.deleteStudent(id);
    }

    @DeleteMapping("/")
    public APIResponse<Student> removeAllStudents() {
        logger.trace("Received DELETE request at / endpoint to remove all students at trace level");
        logger.info("Received DELETE request at / endpoint to remove all students at info level");
        return serviceHelper.deleteAllStudents();

    }

    @GetMapping("/count")
    public ResponseEntity<Long> countStudents() {
        logger.trace("Received GET request at /count endpoint to count students at trace level");
        logger.info("Received GET request at /count endpoint to count students at info level");
        long studentCount=serviceHelper.countStudents();
        return ResponseEntity.status(HttpStatus.OK).body(studentCount);
    }*/
}