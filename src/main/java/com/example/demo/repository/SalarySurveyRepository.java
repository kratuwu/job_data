package com.example.demo.repository;

import com.example.demo.entity.SalarySurveyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalarySurveyRepository extends JpaRepository<SalarySurveyEntity, String> {
}
