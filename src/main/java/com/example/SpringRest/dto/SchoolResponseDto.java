package com.example.SpringRest.dto;

import java.util.List;

public record SchoolResponseDto(
        String name,
        List<StudentResponseDto> student
) {
}
