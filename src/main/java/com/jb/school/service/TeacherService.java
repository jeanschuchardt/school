package com.jb.school.service;

import com.jb.school.entity.Teacher;
import com.jb.school.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    @Autowired
    TeacherRepository teacherRepository;

    public Teacher getTeacherById(Long id){
        return teacherRepository.findById(id).orElseThrow(()->new RuntimeException("teacher not find"));
    }

    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    public Teacher addTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }
}
