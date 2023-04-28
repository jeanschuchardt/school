package com.jb.school.controller;

import com.jb.school.dto.BulkCreationDTO;
import com.jb.school.dto.CourseDTO;
import com.jb.school.dto.CreateCourseAndTeacherDTO;
import com.jb.school.entity.Course;
import com.jb.school.service.CourseAndTeacherService;
import com.jb.school.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course/")
public class CourseController {
    
    private final CourseService courseServiceImp;
    private final CourseAndTeacherService courseAndTeacherService;
    
    public CourseController(CourseService courseServiceImp, CourseAndTeacherService courseAndTeacherService) {
        this.courseServiceImp = courseServiceImp;
        this.courseAndTeacherService = courseAndTeacherService;
    }
    
    
    @PostMapping("/create")
    public Course createCourse(@RequestBody Course course) {
        return courseServiceImp.createCourse(course);
    }
    
    @PostMapping("/createCourseAndTeacher")
    public Course createBoth(@RequestBody CreateCourseAndTeacherDTO createCourseAndTeacherDTO) {
        return courseAndTeacherService.createCourseAndTeacher(createCourseAndTeacherDTO);
    }
    
    @PostMapping("/bulkCreateCourseAndTeacher")
    public List<CourseDTO> bulkCreateBoth(@RequestBody BulkCreationDTO bulkCreationDTO) {
        return courseAndTeacherService.bulkCreateCourseAndTeacher(bulkCreationDTO);
    }
    
    @PutMapping("/{courseId}/{teacherId}")
    public Course addTeacherToCourse(@PathVariable("courseId") Long courseId,
                                     @PathVariable("teacherId") Long teacherId) {
        return courseAndTeacherService.addTeacherToCourse(courseId, teacherId);
    }
    
    @GetMapping("")
    public List<CourseDTO> getAll() {
        return courseServiceImp.getAll();
    }
}
