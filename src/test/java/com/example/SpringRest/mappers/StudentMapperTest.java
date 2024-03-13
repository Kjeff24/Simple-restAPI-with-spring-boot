package com.example.SpringRest.mappers;

import com.example.SpringRest.dto.StudentDto;
import com.example.SpringRest.dto.StudentResponseDto;
import com.example.SpringRest.models.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {

    private StudentMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new StudentMapper();
    }

    /*
     * This is a test method to verify the functionality
     * of the 'toStudent' method in the 'StudentMapper' class.
     * */
    @Test
    public void shouldMapStudentDtoToStudent() {

        StudentDto dto = new StudentDto("John",
                "Arthur",
                "arthur@email.com",
                1);

        Student student = mapper.toStudent(dto);

        assertEquals(dto.firstName(), student.getFirstName());
        assertEquals(dto.lastName(), student.getLastName());
        assertEquals(dto.email(), student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(dto.schoolId(), student.getSchool().getId());

    }

    /*
     * This is a test method to verify the functionality
     * of the 'NullPointerException' of the 'toStudent' method in the 'StudentMapper' class.
     * */
    @Test
    public void should_throw_null_pointer_exception_when_studentDto_is_null(){
        var exp = assertThrows(NullPointerException.class, () -> mapper.toStudent(null));
        assertEquals("The student Dto is null", exp.getMessage());
    }

    /*
     * This is a test method to verify the functionality
     * of the 'toStudentResponseDto' method in the 'StudentMapper' class.
     * */
    @Test
    public void shouldMapStudentToStudentResponseDto(){
        Student student = new Student();

        student.setFirstName("Jeffrey");
        student.setLastName("Arthur");
        student.setEmail("arthur@gmail.com");

        StudentResponseDto studentResponseDto = mapper.toStudentResponseDto(student);

        assertEquals(student.getFirstName(), studentResponseDto.firstName());
        assertEquals(student.getLastName(), studentResponseDto.lastName());
        assertEquals(student.getEmail(), studentResponseDto.email());
    }

}