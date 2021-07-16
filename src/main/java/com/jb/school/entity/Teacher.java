package com.jb.school.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Teacher {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL /*another problem: , fetch = EAGER*/)
    @JsonIgnore
    private List<Course> courses = new ArrayList<>();

}
