package com.example.SpringRest.services;

import com.example.SpringRest.dto.StudentDto;
import com.example.SpringRest.dto.StudentResponseDto;
import com.example.SpringRest.mappers.StudentMapper;
import com.example.SpringRest.models.School;
import com.example.SpringRest.models.Student;
import com.example.SpringRest.repositories.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class StudentServiceTest {
    // StudentService is the testing class
    @InjectMocks
    private StudentService studentService;

    // Declare the dependencies
    @Mock
    private StudentRepository repository;

    @Mock
    private StudentMapper studentMapper;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_save_a_student(){
        // Given
        StudentDto dto = new StudentDto("John",
                "Arthur",
                "arthur@gmail.com",
                1
        );

        Student student = new Student();


        student.setId(1);
        student.setFirstName("John");
        student.setLastName("Arthur");
        student.setEmail("arthur@gmail.com");


        // Mock the calls
        when(studentMapper.toStudent(dto)).thenReturn(student);
        when(repository.save(student)).thenReturn(student);
        when(studentMapper.toStudentResponseDto(student)).thenReturn(new StudentResponseDto("John","Arthur","arthur@gmail.com"));

        // When
        StudentResponseDto responseDto = studentService.saveStudent(dto);

        // Then
        assertEquals(dto.firstName(), responseDto.firstName());
        assertEquals(dto.lastName(), responseDto.lastName());
        assertEquals(dto.email(), responseDto.email());

    }
}