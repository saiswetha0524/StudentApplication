package com.example.StudentApplication.service;

import com.example.StudentApplication.entities.Student;
import com.example.StudentApplication.exception.StudentNotFoundException;
import com.example.StudentApplication.exception.StudentAlreadyExistsException;
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

    public APIResponse<List<Student>> findall() {
        APIResponse<List<Student>> apiResponse = new APIResponse<>();
        Optional<List<Student>> findAllOptional = studentService.findAll();
        if (findAllOptional.isPresent()) {
            List<Student> liststud = findAllOptional.get();
            if (!liststud.isEmpty()) {
                apiResponse.setMessage("List of students successfully fetched");
                apiResponse.setResponse(findAllOptional.get());
                apiResponse.setStatus(StatusEnum.SUCCESS);
            } else {
                throw new StudentNotFoundException("List not found, kindly feed in values");
            }

        }
        return apiResponse;
    }

    public APIResponse<Student> findById(int stud_id) {

        Optional<Student> studentIdOptional = studentService.findById(stud_id);
        APIResponse<Student> apiResponse = new APIResponse<>();
        if (studentIdOptional.isPresent()) {
            apiResponse.setMessage("Student with id - " + stud_id + " details are listed below:");
            apiResponse.setResponse(studentIdOptional.get());
            apiResponse.setStatus(StatusEnum.SUCCESS);
        } else {
            throw new StudentNotFoundException("Student with id - " + stud_id + " details can't be found:");
        }
        return apiResponse;
    }

    public APIResponse<Student> addStudent(Student student) {

        Optional<Student> existingStudentNameOptional = studentService.findByStud_name(student.getName());
        APIResponse<Student> apiResponse = new APIResponse<>();
        if (existingStudentNameOptional.isEmpty()) {
            apiResponse.setResponse(studentService.addStudent(student));
            apiResponse.setMessage("Inserted Successfully");
            apiResponse.setStatus(StatusEnum.SUCCESS);
        } else {
            throw new StudentAlreadyExistsException("Student with name: " + student.getName() + " already exist");
        }
        return apiResponse;


    }

    public APIResponse<Student> deleteStudent(int stud_id) {
        Optional<Student> existingDeletedStudentOptional = studentService.findById(stud_id);
        APIResponse<Student> apiResponse = new APIResponse<>();
        if (existingDeletedStudentOptional.isPresent()) {
            apiResponse.setResponse(studentService.deleteStudent(stud_id));
            apiResponse.setMessage("Student Deleted Successfully");
            apiResponse.setStatus(StatusEnum.SUCCESS);
        } else {
            throw new StudentNotFoundException("Student with id: " + stud_id + " not found");
        }
        return apiResponse;
    }

   /* public APIResponse<Student> deleteAllStudents() {

        Optional<List<Student>> studentOptional = studentService.findAll();
        APIResponse<Student> apiResponse = new APIResponse<>();
        if (studentOptional.isPresent()) {
            List<Student> listStudents = studentOptional.get();
            if (listStudents.isEmpty()) {
                throw new StudentNotFoundException("Student table is empty");
            } else {
                studentService.deleteAllStudents();
                apiResponse.setMessage("Students Deleted Successfully");
                apiResponse.setStatus(StatusEnum.SUCCESS);
            }
        }
        return apiResponse;
    }*/
    //directly delete everything, if any exception thrown,
    // catch(if half record found messge) and handle. if no exception, all record deleted successfully message.

    public APIResponse<Student> deleteAllStudents() {

        Optional<List<Student>> studentOptional = studentService.findAll();
        APIResponse<Student> apiResponse = new APIResponse<>();
        if (studentOptional.isPresent()) {
            List<Student> listStudents = studentOptional.get();
            if (listStudents.isEmpty()) {
                throw new StudentNotFoundException("Student table is empty");
            } else {
                studentService.deleteAllStudents();
                apiResponse.setMessage("Students Deleted Successfully");
                apiResponse.setStatus(StatusEnum.SUCCESS);
            }
        }
        return apiResponse;
    }

    public long countStudents() {
        return studentService.countStudents();
    }
}
