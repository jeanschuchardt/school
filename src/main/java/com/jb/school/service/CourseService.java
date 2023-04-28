package com.jb.school.service;

import com.jb.school.dto.CourseDTO;
import com.jb.school.entity.Course;

import java.util.List;

public interface CourseService {
    Course createCourse(Course course);
    
    Course getCourseById(Long id);
    
    List<CourseDTO> getAll();
    
}
