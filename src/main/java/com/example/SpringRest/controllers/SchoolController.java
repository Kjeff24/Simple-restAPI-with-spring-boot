package com.example.SpringRest.controllers;

import com.example.SpringRest.dto.SchoolDto;
import com.example.SpringRest.dto.SchoolResponseDto;
import com.example.SpringRest.models.School;
import com.example.SpringRest.services.SchoolService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping("/schools")
    public List<SchoolResponseDto> findAllSchools(){

        return schoolService.findAllSchools();
    }

    @PostMapping("/schools")
    public SchoolDto createSchool(@Valid @RequestBody SchoolDto schoolDto){
        return this.schoolService.createSchool(schoolDto);
    }

    @GetMapping("/schools/{school-id}")
    public Optional<School> findSchoolById(@PathVariable("school-id") Integer id){
        return schoolService.findSchoolById(id);
    }
}
