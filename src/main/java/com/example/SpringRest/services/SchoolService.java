package com.example.SpringRest.services;

import com.example.SpringRest.dto.SchoolDto;
import com.example.SpringRest.dto.SchoolResponseDto;
import com.example.SpringRest.mappers.SchoolMapper;
import com.example.SpringRest.models.School;
import com.example.SpringRest.repositories.SchoolRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SchoolService {

    private final SchoolRepository repository;

    private final SchoolMapper schoolMapper;

    public SchoolService(SchoolRepository repository, SchoolMapper schoolMapper) {
        this.repository = repository;
        this.schoolMapper = schoolMapper;
    }

    public List<SchoolResponseDto> findAllSchools(){

        return repository.findAll()
                .stream()
                .map(schoolMapper::toSchoolResponseDto)
                .collect(Collectors.toList());
    }

    public SchoolDto createSchool(SchoolDto schoolDto){
        var school = schoolMapper.toSchool(schoolDto);
        var savedSchool = repository.save(school);
        return schoolMapper.toSchoolDto(savedSchool);
    }

    public Optional<School> findSchoolById(Integer id){
        return repository.findById(id);
    }


}
