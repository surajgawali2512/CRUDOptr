package com.demo.service;

import com.demo.model.Student;
import com.demo.model.Teacher;
import com.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Student> findStudent(Long id){
        return  studentRepository.findById(id);
    }
    public  boolean deleteStudent(Long id){
        if (id!=null) {
            studentRepository.deleteById(id);
            return  true;
        }
        throw new RuntimeException("Unable to Delete");

    }
}
