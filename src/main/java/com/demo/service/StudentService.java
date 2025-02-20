package com.demo.service;

import com.demo.model.Student;
import com.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    public  StudentService(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }
    public List<Student> getAllStudent(){
        return studentRepository.findAll();
    }
    public  Student saveStudent(Student student){
        return studentRepository.save(student);
    }
}
