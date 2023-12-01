package com.example.StudentApplication.controller;

import com.example.StudentApplication.entities.Student;
import com.example.StudentApplication.exception.StudentNotFoundException;
import com.example.StudentApplication.exception.StudentAlreadyExistsException;
import com.example.StudentApplication.models.APIResponse;
import com.example.StudentApplication.models.StatusEnum;
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
    public ResponseEntity<APIResponse<List<Student>>> getAllStudents() {
        APIResponse<List<Student>> apiResponse = new APIResponse<>();

        try {
            apiResponse = serviceHelper.findall();
        } catch (StudentNotFoundException c) {
            apiResponse.setMessage(c.getMessage());
            apiResponse.setStatus(StatusEnum.FAILURE);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
     // return ResponseEntity.status(HttpStatus.OK).body(serviceHelper.findall());
    }

    @GetMapping("/{id}")
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
        } catch (StudentNotFoundException c) {
             apiResponse.setMessage(c.getMessage());
            apiResponse.setStatus(StatusEnum.FAILURE);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }


    @PostMapping("/")
    public ResponseEntity<APIResponse<Student>> addStudent(@RequestBody Student stud) {
        APIResponse<Student> apiResponse = new APIResponse<>();

        try{
            apiResponse=serviceHelper.addStudent(stud);
        }
        catch (StudentAlreadyExistsException sae){
            apiResponse.setMessage(sae.getMessage());
            apiResponse.setStatus(StatusEnum.FAILURE);
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(apiResponse);
        }catch (Exception e){
            apiResponse.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Student>> removeStudent(@PathVariable int stud_id) {
        APIResponse<Student> apiResponse = new APIResponse<>();

        try{
            apiResponse=serviceHelper.deleteStudent(stud_id);
        }
        catch (StudentNotFoundException snf){
            apiResponse.setMessage(snf.getMessage());
            apiResponse.setStatus(StatusEnum.FAILURE);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("/")
    public ResponseEntity<APIResponse<Student>> removeAllStudents() {
        APIResponse<Student> apiResponse = new APIResponse<>();

        try{
            apiResponse=serviceHelper.deleteAllStudents();
        }
        catch (StudentNotFoundException snf){
            long remainingCount=serviceHelper.countStudents();
            if(remainingCount>0){
                serviceHelper.deleteAllStudents();
                apiResponse.setMessage("Partial deletion occurred due to system shut down" +
                        ". Now remaining students deleted");
            }else {
                apiResponse.setMessage(snf.getMessage());
                apiResponse.setStatus(StatusEnum.FAILURE);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countStudents() {
        long studentCount=serviceHelper.countStudents();
        return ResponseEntity.status(HttpStatus.OK).body(studentCount);
    }
}