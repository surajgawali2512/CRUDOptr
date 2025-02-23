package com.demo.service;

import com.demo.model.Teacher;
import com.demo.repository.TeacherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
//    @Autowired
//    TeacherRepository teacherRepository;
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
public  boolean deleteTeacher(Long id){
        if (id!=null&&findTeacher(id).isPresent()) {
            teacherRepository.deleteById(id);
        return  true;
        }

        throw new RuntimeException("Fatal error for Delete");

}

    public List<Teacher> getTeacherByName(String name) {
       if (name!=null) {
           return teacherRepository.findByName(name);
       }
                throw  new RuntimeException("Teacher not found");
    }

    public Optional<Teacher> getTeacherByEmail(String email) {
        return teacherRepository.findByEmail(email);
    }
}
