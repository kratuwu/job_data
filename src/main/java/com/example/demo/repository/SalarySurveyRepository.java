package com.example.demo.repository;

import com.example.demo.entity.SalarySurveyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalarySurveyRepository extends JpaRepository<SalarySurveyEntity, String> {
//    List<SalarySurveyEntity> findBySalaryGreaterThanEqual(Double minSalary);
//
//    List<SalarySurveyEntity> findBySalaryLessThanEqual(Double maxSalary);
//
//    List<SalarySurveyEntity> findBySalaryBetween(Double minSalary, Double maxSalary);
}
