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
    public ResponseEntity<String> hello() {
        return ResponseEntity.status(HttpStatus.OK).body(serviceHelper.helloMethod());
    }

    @GetMapping("/")
    public  ResponseEntity<APIResponse<List<Student>>> getAllStudents() {
        return ResponseEntity.status(HttpStatus.OK).body(serviceHelper.findall());
    }

    //TODO: Implement RESPONSE return type to all the endpoints

    @GetMapping("/{stud_id}")
    public ResponseEntity<APIResponse<Student>> getStudentById(@PathVariable int stud_id) {
      /* Optional<ResponseEntity<APIResponse<Student>>> newOptional= this.getStudentById(stud_id);

        if (newOptional.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(serviceHelper.findById(stud_id));
        }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(serviceHelper.findById(stud_id));*/
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(serviceHelper.findById(stud_id));
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
