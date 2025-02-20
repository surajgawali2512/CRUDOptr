package com.demo.Controller;

import com.demo.model.Student;
import com.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    public  StudentController(StudentService studentService){
        this.studentService=studentService;
    }
    @GetMapping
    public List<Student> getAllStudent(){
        return  studentService.getAllStudent();
    }
    @PostMapping
    public  Student createStudent(@RequestBody Student student){
        return  studentService.saveStudent(student);
    }
}
