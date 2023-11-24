package com.example.StudentApplication.StudentEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="newstudent")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int stud_id;
    private String stud_name;
    private String stud_dept;
}
