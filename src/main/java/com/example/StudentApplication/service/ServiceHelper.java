package com.example.StudentApplication.service;

import com.example.StudentApplication.entities.Student;
import com.example.StudentApplication.exception.CustomNotFoundException;
import com.example.StudentApplication.models.APIResponse;
import com.example.StudentApplication.models.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class ServiceHelper {
    @Autowired
    StudentService studentService;

    public String helloMethod() {
        return "hello!";
    }

    public APIResponse<List<Student>> findall() {
        APIResponse<List<Student>> apiResponse = new APIResponse<>();
        Optional<List<Student>> findAllOptional = studentService.findAll();

            if (!findAllOptional.isEmpty()) {
                apiResponse.setMessage("List of students successfully fetched");
                apiResponse.setResponse(findAllOptional.get());
                apiResponse.setStatus(StatusEnum.SUCCESS);
            } else {
                throw new CustomNotFoundException("List not found, kindly feed in values");
            }
        return apiResponse;
    }

    public APIResponse<Student> findById(int stud_id) {
        //TODO: check if the repo response is null, if so throw custom not found exception
        Optional<Student> studentIdOptional = studentService.findById(stud_id);
        APIResponse<Student> apiResponse = new APIResponse<>();
        if (studentIdOptional.isPresent()) {
            apiResponse.setMessage("Student with id - " + stud_id + " details are listed below:");
            apiResponse.setResponse(studentIdOptional.get());
            apiResponse.setStatus(StatusEnum.SUCCESS);
        } else {
           /* apiResponse.setStatus(StatusEnum.FAILURE);
            apiResponse.setMessage("Student with id - " +stud_id +" details can't be found:");*/
            throw new CustomNotFoundException("Student with id - " + stud_id + " details can't be found:");
            //TODO: Throw a exception
        }
        return apiResponse;
}

    public APIResponse<Student> addStudent(Student student){

        APIResponse<Student> apiResponse = new APIResponse<>();
        apiResponse.setResponse(studentService.addStudent(student));
        apiResponse.setMessage("Inserted Successfully");
        apiResponse.setStatus(StatusEnum.SUCCESS);
        return apiResponse;
    }

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
