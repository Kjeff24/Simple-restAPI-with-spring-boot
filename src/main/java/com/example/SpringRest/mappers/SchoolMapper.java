package com.example.SpringRest.mappers;

import com.example.SpringRest.dto.SchoolDto;
import com.example.SpringRest.dto.SchoolResponseDto;
import com.example.SpringRest.models.School;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class SchoolMapper {

    private final StudentMapper studentMapper;

    public SchoolMapper(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    public School toSchool(SchoolDto dto){
        return new School(dto.name());
    }

    public SchoolDto toSchoolDto(School school){
        return new SchoolDto(school.getName());
    }

    public SchoolResponseDto toSchoolResponseDto(School school){

        return new SchoolResponseDto(
                school.getName(),
                school.getStudent()
                        .stream()
                        .map(studentMapper::toStudentResponseDto)
                        .collect(Collectors.toList())
        );
    }
}
