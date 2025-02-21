package com.demo.service;

import com.demo.model.Teacher;
import com.demo.repository.TeacherRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    public  TeacherService(TeacherRepository teacherRepository){
        this.teacherRepository=teacherRepository;
    }
    public List<Teacher> getAllTeacher(){
        return  teacherRepository.findAll();
    }
    public Teacher saveTeacher(Teacher teacher){
        if (teacher!=null) return  teacherRepository.save(teacher);
        throw new RuntimeException("Teacher is not Saved");
    }
    public Optional<Teacher> findTeacher(Long id){
        return  teacherRepository.findById(id);
    }

}
