package com.example.SpringRest.services;

import com.example.SpringRest.dto.StudentDto;
import com.example.SpringRest.dto.StudentResponseDto;
import com.example.SpringRest.mappers.StudentMapper;
import com.example.SpringRest.models.Student;
import com.example.SpringRest.repositories.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

        Student student = new Student(
                "John",
                "Arthur",
                "arthur@gmail.com",
                20
        );


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

    @Test
    public void should_return_all_students(){

        Student student = new Student(
                "John",
                "Arthur",
                "arthur@gmail.com",
                20
        );

        List<Student> students = new ArrayList<>();
        students.add(student);

        when(repository.findAll()).thenReturn((students));
        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto("John", "Arthur", "arthur@gmail.com"));

        List<StudentResponseDto> dto = studentService.findAllStudent();

        assertEquals(students.size(), dto.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void should_return_student_by_id(){
        Integer studentId = 1;
        Student student = new Student(
                "John",
                "Arthur",
                "arthur@gmail.com",
                20
        );

        student.setId(1);

        when(repository.findById(studentId)).thenReturn(Optional.of(student));
        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto("John", "Arthur", "arthur@gmail.com"));

        StudentResponseDto dto = studentService.findStudentById(studentId);

        assertEquals(dto.firstName(), student.getFirstName());
        assertEquals(dto.lastName(), student.getLastName());
        assertEquals(dto.email(), student.getEmail());

        verify(repository, times(1)).findById(studentId);
    }

    @Test
    public void should_return_find_all_by_first_name(){
        String studentName = "Je";

        Student student1 = new Student(
                "John",
                "Arthur",
                "arthur@gmail.com",
                20
        );

        Student student2 = new Student(
                "Jeffery",
                "Arthur",
                "arthur@gmail.com",
                20
        );

        Student student3 = new Student(
                "Jerry",
                "Arthur",
                "arthur@gmail.com",
                20
        );

        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);

        when(repository.findAllByFirstNameContaining(studentName)).thenReturn((students));
        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto("Jeffery", "Arthur", "arthur@gmail.com"));


        List<StudentResponseDto> dto = studentService.findStudentByFirstName(studentName);

        assertEquals(students.size(), dto.size());
        verify(repository, times(1)).findAllByFirstNameContaining(studentName);

    }

    @Test
    public void should_delete_by_id(){

        Integer studentId = 1;


        studentService.delete(studentId);

        verify(repository, times(1)).deleteById(studentId);
    }
}