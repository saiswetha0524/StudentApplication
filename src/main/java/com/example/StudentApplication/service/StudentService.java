package com.example.StudentApplication.service;

import com.example.StudentApplication.entities.Student;
import com.example.StudentApplication.repo.StudentRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepo studentRepo;

    private static final Logger logger= LoggerFactory.getLogger(StudentService.class);

    public Optional<List<Student>> findAll() {
        logger.info("Fetching all students from the database");
        return Optional.of(studentRepo.findAll());
    }

    public Optional<Student> findById(int stud_id) {
        logger.info("Fetching student with id from the database" +stud_id);
        return studentRepo.findById(stud_id);
    }

    public Optional<Student> findByStud_name(String name) {
        logger.info("Fetching student details with name from the database" +name);
        return studentRepo.findByName(name);
    }

    public Student addStudent(Student student) {
        logger.info("Adding student: {}", student);
        return studentRepo.save(student);
    }

    public void deleteStudent(int stud_id) {
        logger.info("Deleting student by id: {}" +stud_id);
        studentRepo.deleteById(stud_id);}

    public void deleteAllStudents() {
        logger.info("Deleting all students from the database");
        studentRepo.deleteAll();}

    public long countStudents() {
        logger.info("Counting students in the database");
        return studentRepo.countBy();}
}
