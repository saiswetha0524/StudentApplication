package StudentService;

import StudentEntity.Student;
import StudentRepository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServ {
    @Autowired
    StudentRepo studentRepo;

    public List<Student> findall(){
        return studentRepo.findAll();
    }

    public Optional<Student> findById(int stud_id){
        return studentRepo.findById(stud_id);
    }

    public Student addStudent(Student student){
       return studentRepo.save(student);
    }

    public void deleteStudent(int stud_id){
        studentRepo.deleteAllById(Collections.singleton(stud_id));
    }

    public void deleteAllStudents(){
        studentRepo.deleteAll();
    }
}
