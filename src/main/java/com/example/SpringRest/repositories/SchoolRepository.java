package com.example.SpringRest.repositories;

import com.example.SpringRest.models.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Integer> {
}
