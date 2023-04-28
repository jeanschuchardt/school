package com.jb.school.service;

import com.jb.school.dto.CourseDTO;
import com.jb.school.entity.Course;
import com.jb.school.repository.CourseRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImp implements CourseService {
    
    private final CourseRepository courseRepository;
    
    public CourseServiceImp(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }
    
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new RuntimeException("course not find"));
    }
    
    public List<CourseDTO> getAll() {
        List<Course> all = courseRepository.findAll();
        
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setSkipNullEnabled(true);
        List<CourseDTO> CourseDTO = all.stream().map(x -> mapper.map(x, CourseDTO.class)).collect(Collectors.toList());
        
        return CourseDTO;
    }
    
}
