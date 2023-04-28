package com.jb.school.service;

import com.jb.school.dto.BulkCreationDTO;
import com.jb.school.dto.CourseDTO;
import com.jb.school.dto.CreateCourseAndTeacherDTO;
import com.jb.school.entity.Course;

import java.util.List;

public interface CourseAndTeacherService {
    Course addTeacherToCourse(Long courseId, Long teacherId);
    
    List<CourseDTO> bulkCreateCourseAndTeacher(BulkCreationDTO bulkCreationDTO);
    
    Course createCourseAndTeacher(CreateCourseAndTeacherDTO createCourseAndTeacherDTO);
    
}
