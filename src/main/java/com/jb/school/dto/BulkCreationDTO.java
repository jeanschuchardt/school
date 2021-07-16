package com.jb.school.dto;

import lombok.Data;

import java.util.List;

@Data
public class BulkCreationDTO {
    List<CreateCourseAndTeacherDTO> courseAndTeacherDTOList;

}
