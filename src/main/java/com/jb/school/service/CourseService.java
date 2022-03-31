package com.jb.school.service;

import com.jb.school.dto.BulkCreationDTO;
import com.jb.school.dto.CourseDTO;
import com.jb.school.dto.CourseMapper;
import com.jb.school.dto.CreateCourseAndTeacherDTO;
import com.jb.school.entity.Course;
import com.jb.school.entity.Teacher;
import com.jb.school.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherService teacherService;

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course addTeacherToCourse(Long courseId, Long teacherId) {
        Course course = getCourseById(courseId);
        Teacher teacher = teacherService.getTeacherById(teacherId);
        course.setTeacher(teacher);
        courseRepository.save(course);

        return course;
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new RuntimeException("course not find"));
    }

    public List<CourseDTO> getAll() {
        List<Course> all = courseRepository.findAll();

//        ModelMapper mapper = new ModelMapper();
//        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setSkipNullEnabled(true);
//        List<CourseDTO> CourseDTO = all.stream().map(x -> mapper.map(x, CourseDTO.class)).collect(Collectors.toList());

        List<CourseDTO> collect = all.stream().map(x -> CourseMapper.INSTANCE.courseDTO(x)).collect(Collectors.toList());

        return collect;
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

        return getAll();
    }
}
