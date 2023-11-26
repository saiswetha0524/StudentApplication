package com.example.StudentApplication.service;

import com.example.StudentApplication.entities.Student;
import com.example.StudentApplication.models.APIResponse;
import com.example.StudentApplication.models.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ServiceHelper {

    @Autowired
    StudentService studentService;

    public String helloMethod() {
        return "hello!";
    }

    public APIResponse<List<Student>> findall(){

        APIResponse<List<Student>> apiResponse = new APIResponse<>();
        apiResponse.setStatus(StatusEnum.SUCCESS);
        apiResponse.setMessage("List of student successfully fetched");
        apiResponse.setResponse(studentService.findAll());
        return apiResponse;

    }

    public APIResponse<Student> findById(int stud_id){

        Optional<Student> studentIdOptional = studentService.findById(stud_id);
        APIResponse<Student> apiResponse = new APIResponse<>();
        if (studentIdOptional.isPresent()) {
            apiResponse.setMessage("Student with id - " +stud_id +" details are listed below:");
            apiResponse.setResponse(studentIdOptional.get());
            apiResponse.setStatus(StatusEnum.SUCCESS);
        } else {
            apiResponse.setStatus(StatusEnum.FAILURE);
            apiResponse.setMessage("Student with id - " +stud_id +" details can't be found:");
        }
        return apiResponse;
    }

    public APIResponse<Student> addStudent(Student student){

        APIResponse<Student> apiResponse = new APIResponse<>();
        apiResponse.setResponse(studentService.addStudent(student));
        apiResponse.setMessage("Inserted Successfully");
        apiResponse.setStatus(StatusEnum.SUCCESS);
        return apiResponse;    }

    public APIResponse<Student> deleteStudent(int stud_id){

        APIResponse<Student> apiResponse = new APIResponse<>();
        apiResponse.setResponse(studentService.deleteStudent(stud_id));
        apiResponse.setMessage("Student Deleted Successfully");
        apiResponse.setStatus(StatusEnum.SUCCESS);
        return apiResponse;
    }

    public APIResponse<Student> deleteAllStudents(){

        APIResponse<Student> apiResponse = new APIResponse<>();
        apiResponse.setResponse(studentService.deleteAllStudents());
        apiResponse.setMessage("Students Deleted Successfully");
        apiResponse.setStatus(StatusEnum.SUCCESS);
        return apiResponse;

    }


}
