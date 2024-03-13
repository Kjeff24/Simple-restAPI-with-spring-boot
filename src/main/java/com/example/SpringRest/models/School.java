package com.example.SpringRest.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Entity
public class School {
    @Id
    @GeneratedValue
    private Integer id;
    @NotEmpty
    private String name;

    public School() {
    }

    public School(String name) {
        this.name = name;
    }

    @OneToMany(
        mappedBy = "school",
        cascade = CascadeType.ALL
    )
    @JsonManagedReference
    private List<Student> student;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudent() {
        return student;
    }

    public void setStudent(List<Student> student) {
        this.student = student;
    }
}
