package com.example.SpringRest.mappers;

import com.example.SpringRest.dto.SchoolDto;
import com.example.SpringRest.dto.SchoolResponseDto;
import com.example.SpringRest.dto.StudentResponseDto;
import com.example.SpringRest.models.School;
import com.example.SpringRest.models.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SchoolMapperTest {

    private SchoolMapper mapper;

    // Declare the dependency
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        studentMapper = new StudentMapper();
        mapper = new SchoolMapper(studentMapper);
    }

    /*
     * This is a test method to verify the functionality
     * of the 'toSchool' method in the 'SchoolMapper' class.
     * */
    @Test
    public void shouldMapSchoolDtoToSchool(){
        SchoolDto dto = new SchoolDto("St. John");

        School school = mapper.toSchool(dto);

        assertEquals(dto.name(), school.getName());
    }
    /*
    * This is a test method to verify the functionality
    * of the 'toSchoolDto' method in the 'SchoolMapper' class.
    * */
    @Test
    public void shouldMapSchoolToSchoolDto(){
        School school = new School("St. Peters");

        SchoolDto dto = mapper.toSchoolDto(school);

        assertEquals(school.getName(), dto.name());
    }

    /*
     * This is a test method to verify the functionality
     * of the 'toSchoolResponseDto' method in the 'SchoolMapper' class.
     * */
    @Test
    public void shouldMapSchoolToSchoolResponseDto(){

        // Get school with student created
        School school = getSchool();

        // Map the school to a  SchoolResponseDto
        SchoolResponseDto dto = mapper.toSchoolResponseDto(school);

        // Convert each student in the school to a StudentResponseDto and collect them into a list
        List<StudentResponseDto> schoolStudentResponseDtoList = school.getStudent().stream().map(studentMapper::toStudentResponseDto).toList();

        // Assert that the name of the school is correctly mapped to the SchoolResponseDto
        assertEquals(school.getName(), dto.name());
        // Assert that the number of students in the school is correctly reflected in the SchoolResponseDto
        assertEquals(school.getStudent().size(), dto.student().size());
        /*
        * Assert that the list of StudentResponseDto objects in the SchoolResponseDto
        * matches the list created from the students in the school
        */
        assertEquals(schoolStudentResponseDtoList, dto.student());

    }

    private static School getSchool() {
        Student student1 = new Student();
        student1.setFirstName("Jeffrey");
        student1.setLastName("Arthur");
        student1.setEmail("arthur@gmail.com");

        Student student2 = new Student();
        student2.setFirstName("Micheal");
        student2.setLastName("Evans");
        student2.setEmail("evans@gmail.com");

        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);

        // Creating a school and adding the student to it
        School school =new School();
        school.setName("St. Thomas");
        school.setStudent(students);
        return school;
    }

}