package com.jb.school.controller;

import com.jb.school.dto.BulkCreationDTO;
import com.jb.school.dto.CourseDTO;
import com.jb.school.dto.CreateCourseAndTeacherDTO;
import com.jb.school.entity.Course;
import com.jb.school.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course/")
public class CourseController {

    @Autowired
    CourseService courseService;

    @PostMapping("/create")
    public Course createCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    @PostMapping("/createCourseAndTeacher")
    public Course createBoth(@RequestBody CreateCourseAndTeacherDTO createCourseAndTeacherDTO) {
        return courseService.createCourseAndTeacher(createCourseAndTeacherDTO);
    }

    @PostMapping("/bulkCreateCourseAndTeacher")
    public List<CourseDTO> bulkCreateBoth(@RequestBody BulkCreationDTO bulkCreationDTO) {
        return courseService.bulkCreateCourseAndTeacher(bulkCreationDTO);
    }

    @PutMapping("/{courseId}/{teacherId}")
    public Course addTeacherToCourse( @PathVariable("courseId") Long courseId,@PathVariable("teacherId") Long teacherId) {
        return courseService.addTeacherToCourse(courseId,teacherId);
    }

    @GetMapping("")
    public List<CourseDTO> getAll() {
        return courseService.getAll();
    }
}
