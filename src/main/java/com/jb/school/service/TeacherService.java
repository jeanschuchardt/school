package com.jb.school.service;

import com.jb.school.dto.TeacherDTO;
import com.jb.school.dto.TeacherMapper;
import com.jb.school.entity.Teacher;
import com.jb.school.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherService {
    @Autowired
    TeacherRepository teacherRepository;

    public Teacher getTeacherById(Long id){
        return teacherRepository.findById(id).orElseThrow(()->new RuntimeException("teacher not find"));
    }

    public List<Teacher> getAll() {
        List<Teacher> all = teacherRepository.findAll();
        List<TeacherDTO> collect = all.stream().map(x -> TeacherMapper.INSTANCE.teacherDto(x)).collect(Collectors.toList());
        return teacherRepository.findAll();
    }

    public Teacher addTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }
}
