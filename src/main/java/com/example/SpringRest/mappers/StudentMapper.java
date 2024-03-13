package com.example.SpringRest.mappers;

import com.example.SpringRest.dto.StudentDto;
import com.example.SpringRest.dto.StudentResponseDto;
import com.example.SpringRest.models.School;
import com.example.SpringRest.models.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {

    public Student toStudent(StudentDto dto){
        if(dto == null){
            throw new NullPointerException("The student Dto is null");
        }

        var student = new Student();
        var school = new School();

        student.setFirstName(dto.firstName());
        student.setLastName(dto.lastName());
        student.setEmail(dto.email());
        school.setId(dto.schoolId());
        student.setSchool(school);

        return student;
    }

    public StudentResponseDto toStudentResponseDto(Student student){
        return new StudentResponseDto(
                student.getFirstName(),
                student.getLastName(),
                student.getEmail()
        );
    }
}
