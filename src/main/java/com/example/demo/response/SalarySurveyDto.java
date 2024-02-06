package com.example.demo.response;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class SalarySurveyDto {
    private String timestamp;
    private String employer;
    private String location;
    private String jobTitle;
    private String yearsAtEmployer;
    private String yearsOfExperience;
    private String salary;
    private String signingBonus;
    private String annualBonus;
    private String annualStockValueBonus;
    private String gender;
    private String additionalComments;

    // Constructors, getters, and setters
}