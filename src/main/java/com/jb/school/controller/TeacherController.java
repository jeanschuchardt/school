package com.jb.school.controller;

import com.jb.school.entity.Teacher;
import com.jb.school.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher/")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @PutMapping("")
    public Teacher createCourse(@RequestBody Teacher teacher) {
        return teacherService.addTeacher(teacher);
    }

    @GetMapping("")
    public List<Teacher> createCourse() {
        return teacherService.getAll();
    }

}
