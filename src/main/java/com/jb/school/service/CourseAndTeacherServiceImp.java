package com.jb.school.service;

import com.jb.school.dto.BulkCreationDTO;
import com.jb.school.dto.CourseDTO;
import com.jb.school.dto.CreateCourseAndTeacherDTO;
import com.jb.school.entity.Course;
import com.jb.school.entity.Teacher;
import com.jb.school.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseAndTeacherServiceImp implements CourseAndTeacherService{
    
    private final CourseRepository courseRepository;
    private final TeacherService teacherService;
    private final CourseService courseService;
    
    
    public CourseAndTeacherServiceImp(CourseRepository courseRepository, TeacherService teacherService,
                                      CourseService courseService) {
        this.courseRepository = courseRepository;
        this.teacherService = teacherService;
        this.courseService = courseService;
    }
    
    public Course addTeacherToCourse(Long courseId, Long teacherId) {
        Course course = courseService.getCourseById(courseId);
        Teacher teacher = teacherService.getTeacherById(teacherId);
        course.setTeacher(teacher);
        courseRepository.save(course);
        
        return course;
    }
    
    public Course createCourseAndTeacher(CreateCourseAndTeacherDTO createCourseAndTeacherDTO) {
        
        Teacher teacher = new Teacher();
        
        teacher.setFirstName(createCourseAndTeacherDTO.getFirstName());
        teacher.setLastName(createCourseAndTeacherDTO.getLastName());
        
        teacher = teacherService.addTeacher(teacher);
        
        
        Course course = new Course();
        course.setTitle(createCourseAndTeacherDTO.getCourseTitle());
        course.setTeacher(teacher);
        
        course = courseRepository.save(course);
        
        teacher.getCourses().add(course);
        
        return course;
        
        
    }
    
    public List<CourseDTO> bulkCreateCourseAndTeacher(BulkCreationDTO bulkCreationDTO) {
        bulkCreationDTO.getCourseAndTeacherDTOList().forEach(x -> createCourseAndTeacher(x));
        
        return courseService.getAll();
    }
}
