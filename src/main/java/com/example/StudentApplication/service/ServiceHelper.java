package com.example.StudentApplication.service;

import com.example.StudentApplication.entities.Student;
import com.example.StudentApplication.exception.*;
import com.example.StudentApplication.models.APIResponse;
import com.example.StudentApplication.models.StatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger= LoggerFactory.getLogger(StudentService.class);

    public String helloMethod() {
        logger.info("Executing hello method in servicehelper");
        return "hello!";
    }

    public APIResponse<List<Student>> findall() {
        logger.info("Fetching all students from the database");
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
        logger.info("Fetching student with id from the database" +stud_id);
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
        logger.info("Adding student: {}", student);
        Optional<Student> existingStudentNameOptional = studentService.findByStud_name(student.getName());
        APIResponse<Student> apiResponse = new APIResponse<>();
        if (existingStudentNameOptional.isEmpty()) {
            apiResponse.setResponse(studentService.addStudent(student));
            apiResponse.setMessage("Inserted Successfully");
            apiResponse.setStatus(StatusEnum.SUCCESS);
            logger.info("Student added successfully: {}" +student);
        } else {
            throw new StudentAlreadyExistsException("Student with name: " + student.getName() + " already exist");
        }
        return apiResponse;


    }

    public APIResponse<Student> deleteStudent(int stud_id) {
        logger.info("Deleting student by id: {}" +stud_id);
        Optional<Student> existingDeletedStudentOptional = studentService.findById(stud_id);
        APIResponse<Student> apiResponse = new APIResponse<>();
        if (existingDeletedStudentOptional.isPresent()) {
            studentService.deleteStudent(stud_id);
            apiResponse.setMessage("Student Deleted Successfully");
            apiResponse.setStatus(StatusEnum.SUCCESS);
            logger.info("Student deleted successfully with id: {}" +stud_id);
        } else {
            throw new StudentNotFoundException("Student with id: " + stud_id + " not found");
        }
        return apiResponse;
    }

    public APIResponse<Student> deleteAllStudents() {
        logger.info("Deleting all students from the database");
        APIResponse<Student> apiResponse = new APIResponse<>();
        long remainingCount=countStudents();
        Optional<List<Student>> studentOptional = studentService.findAll();
        List<Student> listStudents = studentOptional.get();

        if (!listStudents.isEmpty()) {
        try{
            studentService.deleteAllStudents();
            apiResponse.setMessage("Students Deleted Successfully");
            apiResponse.setStatus(StatusEnum.SUCCESS);
            logger.info("All students deleted successfully");

        }catch (Exception e){
            apiResponse.setMessage("Partial deletion occurred." +
                    ". Now remaining students count is: " +remainingCount);
            logger.error("Error occured during deletion of all students", e);

        }
        }else{
            throw new StudentNotFoundException("Student table is empty");
        }
        return apiResponse;
    }

    public long countStudents() {
        logger.info("Counting students");
        return studentService.countStudents();
    }
}
