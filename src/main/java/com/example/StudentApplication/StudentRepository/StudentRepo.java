package com.example.StudentApplication.StudentRepository;

import com.example.StudentApplication.StudentEntity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository <Student, Integer> {
}
