package com.demo.Controller;

import com.demo.model.Student;
import com.demo.model.Teacher;
import com.demo.service.TeacherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Teachers")
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
}
