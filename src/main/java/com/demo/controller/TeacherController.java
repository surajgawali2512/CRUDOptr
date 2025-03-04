package com.demo.controller;

import com.demo.model.Teacher;
import com.demo.service.TeacherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    final TeacherService teacherService;
    public TeacherController(TeacherService teacherService){
        this.teacherService=teacherService;
    }
    @GetMapping
    public List<Teacher> getAllTeacher(){
        return  teacherService.getAllTeacher();
    }
    @PostMapping
    public  Teacher createTeacher(@RequestBody Teacher teacher){
        return  teacherService.saveTeacher(teacher);
    }
    @GetMapping("/find")
    public Optional<Teacher> findTeacher(@RequestParam Long id){
        return teacherService.findTeacher(id);
    }
    @GetMapping("/delete")
    public  boolean deleteTeacher(@RequestParam Long id){ return  teacherService.deleteTeacher(id);}
    @GetMapping("/name/{name}")
    public List<Teacher> getByName(@PathVariable String name) {
        return teacherService.getTeacherByName(name);
    }
    @GetMapping("/email/{email}")
    public Optional<Teacher> getByEmail(@PathVariable String email) {
        return teacherService.getTeacherByEmail(email);
    }
}
