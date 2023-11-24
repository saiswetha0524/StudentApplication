package com.example.StudentApplication.controller;

import com.example.StudentApplication.entities.Student;
import com.example.StudentApplication.models.Response;
import com.example.StudentApplication.models.StatusEnum;
import com.example.StudentApplication.repo.StudentRepo;
import com.example.StudentApplication.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/studentDetails")
public class StudentController {
    @Autowired
    StudentRepo studentRepo;

    @Autowired
    StudentService studentService;

    @GetMapping("/hi")
    public String hello() {
        return "hello!";
    }

    @GetMapping("/")
    public List<Student> getAllStudents() {
        return studentService.findall();
    }

    //TODO: Implement RESPONSE return type to all the endpoints

    @GetMapping("/{stud_id}")
    public Response getStudentById(@PathVariable int stud_id) {
        Optional<Student> studentOptional = studentService.findById(stud_id);
        Response response = new Response();
        if (studentOptional.isPresent()) {
            response.setResponse(studentOptional.get());
            response.setStatus(StatusEnum.SUCCESS);
        } else {
            response.setStatus(StatusEnum.FAILURE);
            response.setErrorDetails("Id not present.Check Again");
        }
        return response;
    }

    @PostMapping("/")
    public Student addStudent(@RequestBody Student stud) {
        return studentService.addStudent(stud);
    }

    @DeleteMapping("/{stud_id}")
    public void removeStudent(@PathVariable int stud_id) {
        studentService.deleteStudent(stud_id);
    }

    @DeleteMapping("/")
    public void removeAllStudents() {
        studentService.deleteAllStudents();
    }
}
