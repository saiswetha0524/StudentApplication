package com.example.StudentApplication.repo;

import com.example.StudentApplication.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface StudentRepo extends JpaRepository <Student, Integer> {
/*    Optional<Student> findByStud_dept(String dept);*/

   /* @Query("SELECT s FROM Student s WHERE s.name= :name")
    Optional<Student> findByStud_name(@Param("name") String stud_name);
*/
    Optional<Student> findByName(String name);

    long countBy();

}
