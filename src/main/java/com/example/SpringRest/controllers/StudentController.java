package com.example.SpringRest.controllers;

import com.example.SpringRest.dto.StudentDto;
import com.example.SpringRest.dto.StudentResponseDto;
import com.example.SpringRest.models.Student;
import com.example.SpringRest.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public StudentResponseDto saveStudent(@Valid @RequestBody StudentDto dto){
        return this.studentService.saveStudent(dto);
    }

    @GetMapping("/students")
    public List<StudentResponseDto> findAllStudent(){
        return this.studentService.findAllStudent();
    }

    @GetMapping("/students/{student-id}")
    public StudentResponseDto findStudentById(@PathVariable("student-id") Integer id){
        return studentService.findStudentById(id);
    }

    @GetMapping("/students/search/{student-name}")
    public List<StudentResponseDto> findStudentByFirstName(@PathVariable("student-name") String name){
        return studentService.findStudentByFirstName(name);
    }

    @DeleteMapping("/students/{student-id}")
    public void delete(@PathVariable("student-id") Integer id){
        studentService.delete(id);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp){
//        var errors = new HashMap<String, String>();
//        exp.getBindingResult().getAllErrors()
//                .forEach(error -> {
//                    var fieldName = ((FieldError) error).getField();
//                    var errorMessage = error.getDefaultMessage();
//                    errors.put(fieldName, errorMessage);
//                });
//        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//    }

}
