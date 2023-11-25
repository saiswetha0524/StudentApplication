package com.example.StudentApplication.controller;

import com.example.StudentApplication.entities.Student;
import com.example.StudentApplication.models.APIResponse;
import com.example.StudentApplication.models.StatusEnum;
import com.example.StudentApplication.repo.StudentRepo;
import com.example.StudentApplication.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/studentDetails")
public class StudentController {

    //why along with failure msg it is listing down the values if we globally declare responseHandler?
    //Can we set status through response entity too?

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    StudentService studentService;

    APIResponse<Student> apiResponse = new APIResponse<>();


    @GetMapping("/hi")
    public String hello() {
        return "hello!";
    }

    @GetMapping("/")
    public APIResponse<List<Student>> getAllStudents() {
        APIResponse<List<Student>> apiResponse = new APIResponse<>();
        apiResponse.setStatus(StatusEnum.SUCCESS);
        apiResponse.setResponse(studentService.findall());
        return apiResponse;

    }


    //TODO: Implement RESPONSE return type to all the endpoints

    @GetMapping("/{stud_id}")
    public APIResponse<Student> getStudentById(@PathVariable int stud_id) {
        Optional<Student> studentIdOptional = studentService.findById(stud_id);
      //  apiResponse<Student> apiResponse = new apiResponse<>();
        if (studentIdOptional.isPresent()) {
           // apiResponse.setStudents((List<Student>) studentIdOptional.get());
            apiResponse.setResponse(studentIdOptional.get());
            apiResponse.setHttpStatus(HttpStatus.OK);
            apiResponse.setStatus(StatusEnum.SUCCESS);
        } else {
            apiResponse.setStatus(StatusEnum.FAILURE);
            apiResponse.setHttpStatus(HttpStatus.NOT_FOUND);
            apiResponse.setMessage("Id not present.Check Again");
        }
        return apiResponse;
    }

    @PostMapping("/")
    public APIResponse<Student> addStudent(@RequestBody Student stud) {
       // apiResponse<Student> apiResponse = new apiResponse<>();
        apiResponse.setResponse(studentService.addStudent(stud));
        apiResponse.setMessage("Inserted Successfully");
        apiResponse.setStatus(StatusEnum.SUCCESS);
        apiResponse.setHttpStatus(HttpStatus.OK);
        return apiResponse;
       // return studentService.addStudent(stud);
    }

    @DeleteMapping("/{stud_id}")
    public APIResponse<Student> removeStudent(@PathVariable int stud_id) {
       // apiResponse<Student> apiResponse = new apiResponse<>();
        apiResponse.setMessage("Student Deleted Successfully");
        apiResponse.setStatus(StatusEnum.SUCCESS);
        apiResponse.setHttpStatus(HttpStatus.OK);
        return apiResponse;
        //studentService.deleteStudent(stud_id);
    }

    @DeleteMapping("/")
    public APIResponse<Student> removeAllStudents() {
     //   apiResponse<Student> apiResponse = new apiResponse<>();
        apiResponse.setMessage("Students Deleted Successfully");
        apiResponse.setStatus(StatusEnum.SUCCESS);
        apiResponse.setHttpStatus(HttpStatus.OK);
        return apiResponse;
       // studentService.deleteAllStudents();
    }
}
