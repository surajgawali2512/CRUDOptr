package com.demo.controller;

import com.demo.model.Student;
import com.demo.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    @GetMapping("/find")
    public Optional<Student> findTeacher(@RequestParam Long id){
        return studentService.findStudent(id);
    }
    @GetMapping("/delete")
    public  boolean deleteTeacher(@RequestParam Long id){ return  studentService.deleteStudent(id);}
}
