package com.example.StudentApplication.service;

import com.example.StudentApplication.entities.Student;
import com.example.StudentApplication.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepo studentRepo;

    public List<Student> findAll() {
        return studentRepo.findAll();
    }

    public Optional<Student> findById(int stud_id) {
        return studentRepo.findById(stud_id);
    }

    public Student addStudent(Student student) {
        return studentRepo.save(student);
    }

    public Student deleteStudent(int stud_id) {
        studentRepo.deleteById(stud_id);
        return null;
    }

    public Student deleteAllStudents() {
        studentRepo.deleteAll();
        return null;
    }
}
