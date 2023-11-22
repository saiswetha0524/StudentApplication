package StudentController;

import StudentEntity.Student;
import StudentRepository.StudentRepo;
import StudentService.StudentServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/studentDetails")
public class StudentCont {
    @Autowired
    StudentRepo studentRepo;

    @Autowired
    StudentServ studentServ;

    @GetMapping("/hi")
    public String hello(){
        return "hello!";
    }

    @GetMapping("/")
    public List<Student> getAllStudents(){
        return studentServ.findall();
    }

    @GetMapping("/{id}")
    public Optional<Student> getStudentById(@PathVariable int stud_id){
        return Optional.of(studentServ.findById(stud_id).get());
    }

    @PostMapping("/")
    public Student addStudent(@RequestBody Student stud){
        return studentServ.addStudent(stud);
    }

    @DeleteMapping("/{id}")
    public void removeStudent(@PathVariable int stud_id){
        studentServ.deleteStudent(stud_id);
    }

    @DeleteMapping("/")
        public void removeAllStudents(){
        studentServ.deleteAllStudents();
    }
}
