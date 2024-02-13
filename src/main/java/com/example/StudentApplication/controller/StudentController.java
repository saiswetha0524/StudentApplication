package com.example.StudentApplication.controller;

import com.example.StudentApplication.entities.Student;
import com.example.StudentApplication.models.APIResponse;
import com.example.StudentApplication.service.ServiceHelper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/studentDetails")
public class StudentController {
    @Autowired
    ServiceHelper serviceHelper;
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    public final RestTemplate restTemplate;

    @Autowired
    public StudentController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/external-site")
    public String externalSite(){
        String url="https://dummyjson.com/docs";
        ResponseEntity<String> responseEntity=restTemplate.getForEntity(url,String.class);
        return responseEntity.getBody();
    }


    @ApiOperation(notes = "Hello method", value = "Welcome message", nickname = "welcome all",
            tags = {"Welcome"} )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Nice!", response = Student.class),
               })
    @GetMapping(value="/hi",produces = {"application/json", "application/xml"})
    public ResponseEntity<String> hello() {
        logger.info("Received GET request at /hi endpoint");
        return ResponseEntity.status(HttpStatus.OK).body(serviceHelper.helloMethod());
    }
    @ApiOperation(notes = "Returns all Students", value = "Find ALl Student", nickname = "getAllStuds",
            tags = {"Student Details"} )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Nice!", response = Student.class),
            @ApiResponse(code = 400, message = "Error happened", response = com.example.StudentApplication.models.APIResponse.class),
            @ApiResponse(code = 404, message = "Studnet not found", response = com.example.StudentApplication.models.APIResponse.class)
    })
    @GetMapping(value="/",produces = {"application/json", "application/xml"})
    public APIResponse<List<Student>> getAllStudents() {
        logger.trace("Received GET request at / endpoint to fetch all students at trace level");
        logger.info("Received GET request at / endpoint to fetch all students at info level");
        return serviceHelper.findall();
    }
    @ApiOperation(notes = "Returns Students with id", value = "Find Student with id", nickname = "getStudWithId",
            tags = {"Student Details"} )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Nice!", response = Student.class),
            @ApiResponse(code = 400, message = "Error happened", response = com.example.StudentApplication.models.APIResponse.class),
            @ApiResponse(code = 404, message = "Studnet not found", response = com.example.StudentApplication.models.APIResponse.class)
    })
    @GetMapping(value = "/{id}",produces = {"application/json", "application/xml"})
    public APIResponse<Student> getStudentById(@PathVariable int id) {
        logger.trace("Received GET request at /{} endpoint to fetch student by id at trace level", id);
        logger.info("Received GET request at /{} endpoint to fetch student by id at info level", id);
        return serviceHelper.findById(id);
    }
    @ApiOperation(notes = "Adds Students", value = "Add Student", nickname = "additionOfStuds",
            tags = {"Student Details"} )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Nice!", response = Student.class),
            @ApiResponse(code = 400, message = "Error happened", response = com.example.StudentApplication.models.APIResponse.class),
            @ApiResponse(code = 404, message = "Student cant be added/already present", response = com.example.StudentApplication.models.APIResponse.class)
    })
    @PostMapping(value="/",produces = {"application/json", "application/xml"}, consumes = APPLICATION_JSON_VALUE)
    public APIResponse<Student> addStudent(@RequestBody Student stud) {
        logger.trace("Received POST request at / endpoint to add student: {} at trace level");
        logger.info("Received POST request at / endpoint to add student: {} at info level", stud);
        return serviceHelper.addStudent(stud);

    }
    @ApiOperation(notes = "Deletes student with id", value = "Delete with id", nickname = "delStudWithId",
            tags = {"Student Details"} )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Nice!", response = Student.class),
            @ApiResponse(code = 400, message = "Error happened", response = com.example.StudentApplication.models.APIResponse.class),
            @ApiResponse(code = 404, message = "Student can't be deleted", response = com.example.StudentApplication.models.APIResponse.class)
    })
    @DeleteMapping(value="/{id}",produces = {"application/json", "application/xml"})
    public APIResponse<Student> removeStudent(@PathVariable int id) {
        logger.trace("Received DELETE request at /{id} endpoint to remove student by id at trace level", id);
        logger.info("Received DELETE request at /{id} endpoint to remove student by id at info level", id);
        return serviceHelper.deleteStudent(id);
    }
    @ApiOperation(notes = "Deletes All Students", value = "Delete every student", nickname = "delAllStuds",
            tags = {"Student Details"} )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Nice!", response = Student.class),
            @ApiResponse(code = 400, message = "Error happened", response = com.example.StudentApplication.models.APIResponse.class),
            @ApiResponse(code = 404, message = "Student can't be deleted", response = com.example.StudentApplication.models.APIResponse.class)
    })
    @DeleteMapping(value="/",produces = {"application/json", "application/xml"})
    public APIResponse<Student> removeAllStudents() {
        logger.trace("Received DELETE request at / endpoint to remove all students at trace level");
        logger.info("Received DELETE request at / endpoint to remove all students at info level");
        return serviceHelper.deleteAllStudents();

    }
    @ApiOperation(notes = "Returns Students count", value = "Student count", nickname = "countOfStuds",
            tags = {"Student Details"} )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Nice!", response = Student.class),
            @ApiResponse(code = 400, message = "Error happened", response = com.example.StudentApplication.models.APIResponse.class),
            @ApiResponse(code = 404, message = "Students count can't be found", response = com.example.StudentApplication.models.APIResponse.class)
    })
    @GetMapping(value="/count")
    public ResponseEntity<Long> countStudents() {
        logger.trace("Received GET request at /count endpoint to count students at trace level");
        logger.info("Received GET request at /count endpoint to count students at info level");
        long studentCount=serviceHelper.countStudents();
        return ResponseEntity.status(HttpStatus.OK).body(studentCount);
    }
}