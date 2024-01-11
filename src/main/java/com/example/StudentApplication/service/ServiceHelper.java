package com.example.StudentApplication.service;

import com.example.StudentApplication.entities.Student;
import com.example.StudentApplication.exception.*;
import com.example.StudentApplication.models.APIResponse;
import com.example.StudentApplication.models.StatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
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

    private static final String MDC_KEY="method";

    private void setMDCContext(String methodName){
        MDC.put(MDC_KEY,methodName);
    }

    private void clearMDCContext(){
        MDC.remove(MDC_KEY);
    }

    public String helloMethod() {
        setMDCContext("helloMethod");
        try{
        logger.info("Executing hello method in servicehelper");
        logger.debug("Additional debug information for helloMethod");
        return "hello and welcome to my student application!";
    }finally {
            clearMDCContext();
        }
        }

    public APIResponse<List<Student>> findall() {
        setMDCContext("findall");
        try{
        logger.info("Fetching all students from the database");
        logger.debug("Additional debug information for findall Method");
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
    }finally {
            clearMDCContext();
        }
        }

    public APIResponse<Student> findById(int stud_id) {
        setMDCContext("findById");
        try{
        logger.info("Fetching student with id from the database {}" ,stud_id);
        logger.debug("Additional debug information for findById Method");
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
    }finally {
            clearMDCContext();
        }
        }

    public APIResponse<Student> addStudent(Student student) {
        setMDCContext("addStudent");
        try{
        logger.info("Adding student: {}", student);
        logger.debug("Additional debug information for addStudent Method");
        Optional<Student> existingStudentNameOptional = studentService.findByStud_name(student.getName());
        APIResponse<Student> apiResponse = new APIResponse<>();
        if (existingStudentNameOptional.isEmpty()) {
            apiResponse.setResponse(studentService.addStudent(student));
            apiResponse.setMessage("Inserted Successfully");
            apiResponse.setStatus(StatusEnum.SUCCESS);
            logger.info("Student added successfully {}" ,student);
            logger.debug("Additional debug information for Student added successfully {}" ,student);

        } else {
            throw new StudentAlreadyExistsException("Student with name: " + student.getName() + " already exist");
        }
        return apiResponse;

    }finally {
            clearMDCContext();
        }
        }

    public APIResponse<Student> deleteStudent(int stud_id) {
        setMDCContext("deleteStudent");
        try{
        logger.info("Deleting student by id: {}" ,stud_id);
        logger.debug("Additional debug information for deleteStudent Method");

        Optional<Student> existingDeletedStudentOptional = studentService.findById(stud_id);
        APIResponse<Student> apiResponse = new APIResponse<>();
        if (existingDeletedStudentOptional.isPresent()) {
            studentService.deleteStudent(stud_id);
            apiResponse.setMessage("Student Deleted Successfully");
            apiResponse.setStatus(StatusEnum.SUCCESS);
            logger.info("Student deleted successfully with id: {}" ,stud_id);
            logger.debug("Additional debug information for Student deleted successfully with id: {}" ,stud_id);

        } else {
            throw new StudentNotFoundException("Student with id: " + stud_id + " not found");
        }
        return apiResponse;
    }finally {
            clearMDCContext();
        }
        }

    public APIResponse<Student> deleteAllStudents() {
        setMDCContext("deleteAllStudents");
        try{
        logger.info("Deleting all students from the database");
        logger.debug("Additional debug information for deleteAllStudents Method");

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
            logger.debug("Additional debug information for All students deleted successfully");


        }catch (Exception e){
            apiResponse.setMessage("Partial deletion occurred." +
                    ". Now remaining students count is: " +remainingCount);
            logger.error("Error occured during deletion of all students", e);

        }
        }else{
            throw new StudentNotFoundException("Student table is empty");
        }
        return apiResponse;
    }finally {
            clearMDCContext();
        }
        }

    public long countStudents() {
        setMDCContext("countStudents");
        try{
        logger.info("Counting students");
        logger.debug("Additional debug statement for countStudents method");
        return studentService.countStudents();
    }finally {
            clearMDCContext();
        }
        }
}
