package com.jb.school.dto;

import lombok.Data;

@Data
public class CourseDTO {
    private Long id;
    private String title;
    private TeacherDTO teacher;

}
